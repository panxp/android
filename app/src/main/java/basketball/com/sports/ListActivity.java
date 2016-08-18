package basketball.com.sports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<Map<String, Object>> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_list);




        Toast.makeText(ListActivity.this, "正在加载...", Toast.LENGTH_SHORT).show();
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

        SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.list_item,
                new String[]{"title", "img"},
                new int[]{R.id.title, }
        );
        listView.setAdapter(adapter);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.dev.qiwei.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        basketball.com.sports.util.Service service = retrofit.create(basketball.com.sports.util.Service.class);

        Call<ArrayList<Video>> call = service.getList(id);

        call.enqueue(ListActivity.this);



        // Call<List<Video>> repos = service.listRepos("2");
        //System.out.println(repos);
       // Log.i("abc", "-------------");
        // Log.i(repos.toString());
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "加载中...");
        map.put("img", R.drawable.yunqiu);
        list.add(map);
        return list;


    }
    @Override
    public void onFailure(Call<ArrayList<Video>> call, Throwable t) {
        Log.i("failed", t.getMessage());
    }

    @Override
    public void onResponse(Call<ArrayList<Video>> call, Response<ArrayList<Video>> response) {

        final ArrayList<Video> list = response.body();
        VideoAdapter adapter = new VideoAdapter(this, list, R.layout.list_video_item);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String video_url = list.get(position).getUrl();
                //    HashMap<String, String> map = (HashMap<String, String>)listView.getSelectedItem();
                //Object o = listView.getItemAtPosition(position);
                //  VideoAdapter fullObject = (VideoAdapter)o;
                //((VideoAdapter) o));
                //  ArrayList<List> list = (ArrayList<List>) listView.getItemAtPosition(position);
                //  for (int i =0;i<list.size();i++){
                // Toast.makeText(ListActivity.this, "title = " + video_url, Toast.LENGTH_SHORT).show();
                //  }
                //  String title = list.get();
                Intent intent = new Intent(ListActivity.this, VideoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                bundle.putString("title", "title");
                bundle.putString("video_url", video_url);
                int realPosition = (int) id;


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