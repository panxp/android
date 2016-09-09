package basketball.com.sports;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basketball.com.sports.model.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class ListActivity extends AppCompatActivity implements Callback<ArrayList<Video>> {

    private ListView listView;
    private ListView loading;
    private List<Map<String, Object>> list;
    InterstitialAD iad;
    BannerView bv;
    ViewGroup bannerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_list);
        initWindow();
        //Toast.makeText(ListActivity.this, "正在加载...", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 100);

        String title = intent.getStringExtra("title");
        TextView textViewTitle = (TextView) findViewById(R.id.list_title);
        textViewTitle.setText(title);
        //  Toast.makeText(ListActivity.this, "title = "+title, Toast.LENGTH_SHORT).show();
        // View view = inflater.inflate(R.layout.activity_list, container, false);
        //  57b4205ee0f55a34bb000ca2
        //af2a2963
        listView = (ListView) findViewById(R.id.list_video);
        bannerContainer = (ViewGroup) this.findViewById(R.id.bannerContainer);
        this.initBanner();
        this.bv.loadAD();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://video.tibaing.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        basketball.com.sports.util.Service service = retrofit.create(basketball.com.sports.util.Service.class);

        Call<ArrayList<Video>> call = service.getList(id);

        call.enqueue(ListActivity.this);


    }


    private void initBanner() {
        this.bv = new BannerView(this, ADSize.BANNER, Constants.APPID, Constants.BannerPosID);
        bv.setRefresh(30);

        bv.setADListener(new AbstractBannerADListener() {
            @Override
            public void onNoAD(int arg0) {
                Log.i("AD_DEMO", "BannerNoAD，eCode=" + arg0);
            }

            @Override
            public void onADReceiv() {
                Log.i("AD_DEMO", "ONBannerReceive");
                //   bannerContainer.addView(bv);
            }
        });

        bannerContainer.addView(this.bv);
    }


    private InterstitialAD getIAD() {
        if (iad == null) {
            iad = new InterstitialAD(this, Constants.APPID, Constants.InterteristalPosID);
        }
        return iad;
    }

    private void showAD() {
        getIAD().setADListener(new AbstractInterstitialADListener() {

            @Override
            public void onNoAD(int arg0) {
                Log.i("AD_DEMO", "LoadInterstitialAd Fail:" + arg0);
            }

            @Override
            public void onADReceive() {
                Log.i("AD_DEMO", "onADReceive");
                iad.show();
            }
        });
        iad.loadAD();
    }


    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green_main_lcsq));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintResource(R.color.colorNavBar);
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Video>> call, Throwable t) {
        Log.i("failed--", t.getMessage());
    }

    @Override
    public void onResponse(Call<ArrayList<Video>> call, Response<ArrayList<Video>> response) {
        final ArrayList<Video> list = response.body();
        VideoAdapter adapter = new VideoAdapter(this, list, R.layout.list_video_item);
        TextView loading = (TextView) findViewById(R.id.loading);
        loading.setVisibility(View.GONE);
        listView.setAdapter(adapter);
        //showAD();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String video_url = list.get(position).getUrl();
                Intent intent = new Intent(ListActivity.this, VideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                bundle.putString("title", "title");
                bundle.putString("video_url", video_url);
               // int realPosition = (int) id;
                intent.putExtras(bundle);
                 startActivity(intent);
                //FruitList.this.finish();
            }

        });
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    // Toast.makeText(this, "abc"+str+title ,Toast.LENGTH_SHORT).show();

    /**
     * Activity销毁时，销毁adView
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}