package basketball.com.sports;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
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

    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 100);

        String title = intent.getStringExtra("title");
        TextView textViewTitle = (TextView) findViewById(R.id.list_title);
        textViewTitle.setText(title);
        //  Toast.makeText(ListActivity.this, "title = "+title, Toast.LENGTH_SHORT).show();
        // View view = inflater.inflate(R.layout.activity_list, container, false);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.dev.qiwei.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        basketball.com.sports.util.Service service = retrofit.create(basketball.com.sports.util.Service.class);

        Call<ArrayList<Video>> call = service.getList();

        call.enqueue(ListActivity.this);
        listView = (ListView) findViewById(R.id.list_video);


        // Call<List<Video>> repos = service.listRepos("2");
        //System.out.println(repos);
        Log.i("abc", "-------------");
        // Log.i(repos.toString());
    }


    @Override
    public void onFailure(Call<ArrayList<Video>> call, Throwable t) {
        Log.i("failed", t.getMessage());
    }

    @Override
    public void onResponse(Call<ArrayList<Video>> call, Response<ArrayList<Video>> response) {
        ArrayList<Video> list = response.body();
        VideoAdapter adapter = new VideoAdapter(this, list, R.layout.list_video_item);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //    HashMap<String, String> map = (HashMap<String, String>)listView.getSelectedItem();
                //Object o = listView.getItemAtPosition(position);
              //  VideoAdapter fullObject = (VideoAdapter)o;
                //((VideoAdapter) o));
                //  ArrayList<List> list = (ArrayList<List>) listView.getItemAtPosition(position);
                //  for (int i =0;i<list.size();i++){
               // Toast.makeText(ListActivity.this, "title = " + map.get("title"), Toast.LENGTH_SHORT).show();
                //  }
                //  String title = list.get();
                Intent intent = new Intent(ListActivity.this, VideoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                bundle.putString("title", "title");
                int realPosition = (int) id;


                intent.putExtras(bundle);
                startActivity(intent);


                //FruitList.this.finish();
            }

        });
    }


    // Toast.makeText(this, "abc"+str+title ,Toast.LENGTH_SHORT).show();


    private List<Map<String, Object>> getData(List list) {
        List<Map<String, Object>> list2 = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("title", "从菜鸟到高手的篮球训练控球1");
        map.put("time", "31:19");
        map.put("img", R.drawable.one11);
        list2.add(map);
        return list;
    }


}