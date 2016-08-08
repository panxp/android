package basketball.com.sports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 100);

        String title = intent.getStringExtra("title");
        TextView textViewTitle = (TextView) findViewById(R.id.list_title);
        textViewTitle.setText(title);
        //  Toast.makeText(ListActivity.this, "title = "+title, Toast.LENGTH_SHORT).show();
        // View view = inflater.inflate(R.layout.activity_list, container, false);

        listView = (ListView) findViewById(R.id.list_video);


        SimpleAdapter adapter = new SimpleAdapter(this, getData(id.toString()), R.layout.list_video_item,
                new String[]{"title", "time", "img"},
                new int[]{R.id.title, R.id.time, R.id.img}
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                String title = map.get("title");
                Intent intent = new Intent(ListActivity.this, VideoActivity.class);

                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                bundle.putString("title", title);
                int realPosition = (int) id;


                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(ListActivity.this, "id = " + id, Toast.LENGTH_SHORT).show();
                //FruitList.this.finish();
            }
        });


        // Toast.makeText(this, "abc"+str+title ,Toast.LENGTH_SHORT).show();
    }

    private List<Map<String, Object>> getData(String id) {
        List<Map<String, Object>> list = new ArrayList<>();

        if (id == "0") {
            Map<String, Object> map = new HashMap<>();
            map.put("title", "从菜鸟到高手的篮球训练控球1");
            map.put("time", "31:19");
            map.put("img", R.drawable.one11);
            list.add(map);
        }
        return list;
    }


}