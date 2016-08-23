package basketball.com.sports;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AlbumFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("AlbumFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("AlbumFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab02, container, false);


        listView = (ListView) view.findViewById(R.id.album);

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.list_item,
                new String[]{"title", "img"},
                new int[]{R.id.title, R.id.img}
        );
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> map = (HashMap<String, String>) listView.getItemAtPosition(position);
                String title = map.get("title");

                Intent intent = new Intent(getActivity(), ListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id", 1000 + id);
                bundle.putString("title", title);
                //int realPosition = (int) id;


                intent.putExtras(bundle);
                startActivity(intent);
                //     Toast.makeText(getActivity(), "id = " + id, Toast.LENGTH_SHORT).show();
                //FruitList.this.finish();
            }
        });


        return view;
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "NBA明星教学"); //1000
        map.put("img", R.drawable.all_star);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "乔丹篮球教学"); //1001
        map.put("img", R.drawable.qiaodan);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "91篮球教学"); //1002
        map.put("img", R.drawable.jiuyilanqiu);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "加农贝克篮球教学");//1003
        map.put("img", R.drawable.jialongbeike);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "张卫平篮球教学");//1004
        map.put("img", R.drawable.zhangweiping);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("title", "BetterBasketball篮球教学");//1005
        map.put("img", R.drawable.betterbasketball);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "精典篮球教学大杂烩");
        map.put("img", R.drawable.dazahui);
        list.add(map);

        return list;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
