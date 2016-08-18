package basketball.com.sports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    // private ArrayList<String> list = new ArrayList<String>();
    private List<Map<String, Object>> list;
    private int i = 0;

    /**
     * @描述 在onCreateView中加载布局
     */

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("CategoryFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("CategoryFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab01, container, false);
        listView = (ListView) view.findViewById(R.id.list);

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
                bundle.putLong("id", 100+id);
                bundle.putString("title", title);
            //    int realPosition = (int) id;


                intent.putExtras(bundle);
                startActivity(intent);
               // Toast.makeText(getActivity(), "id = " + id, Toast.LENGTH_SHORT).show();
                //FruitList.this.finish();
            }
        });

        return view;

    }




//    private List<String> getData() {
//        List<String> data = new ArrayList<String>();
//        for (int i = 0; i < 20; i++) {
//            data.add(i + "");
//        }
//        return data;
//    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "运球");//100
        map.put("img", R.drawable.yunqiu);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "上篮");//101
        map.put("img", R.drawable.shanglan);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "投篮");//102
        map.put("img", R.drawable.toulan);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "进攻");//103
        map.put("img", R.drawable.jinggong);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "防守");//104
        map.put("img", R.drawable.fangshou);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "篮板");//105
        map.put("img", R.drawable.lanban);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "传球");//106
        map.put("img", R.drawable.chuanqiu);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "弹跳");//107
        map.put("img", R.drawable.tantiao);
        list.add(map);
        return list;


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
