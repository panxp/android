package basketball.com.sports;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;
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


public class RefereeFragment extends Fragment implements AdapterView.OnItemClickListener, Callback<ArrayList<Video>> {
    private ListView listView;
    private TextView loading;
    private View view;
    InterstitialAD iad;

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("RefereeFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("RefereeFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.tab04, container, false);
        listView = (ListView) view.findViewById(R.id.referee);
        // Log.i("===",listView==null?"nullll":"good");

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://video.tibaing.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        basketball.com.sports.util.Service service = retrofit.create(basketball.com.sports.util.Service.class);

        long id = 500;
        Call<ArrayList<Video>> call = service.getList(id);

        call.enqueue(RefereeFragment.this);


        return view;
    }


    @Override
    public void onFailure(Call<ArrayList<Video>> call, Throwable t) {
        Log.i("failed--", t.getMessage());
    }

    @Override
    public void onResponse(Call<ArrayList<Video>> call, Response<ArrayList<Video>> response) {
        final ArrayList<Video> list = response.body();
        VideoAdapter adapter = new VideoAdapter(getActivity(), list, R.layout.list_video_item);
//        for (int i = 0; i < list.size(); i++) {
//            Log.i("ssss", list.get(i).getTitle());
//        }
        TextView loading = (TextView) view.findViewById(R.id.loading_referee);
        loading.setVisibility(View.GONE);
        listView.setAdapter(adapter);
        showAD();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String video_url = list.get(position).getUrl();
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                bundle.putString("title", "title");
                bundle.putString("video_url", video_url);
                intent.putExtras(bundle);
                startActivity(intent);

                //FruitList.this.finish();
            }

        });

    }

    private InterstitialAD getIAD() {
        if (iad == null) {
            iad = new InterstitialAD(getActivity(), Constants.APPID, Constants.InterteristalPosID);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
