package basketball.com.sports;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.support.v4.app.ListFragment;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeixinFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    // private ArrayList<String> list = new ArrayList<String>();
    private List<Map<String, Object>> list;
    private int i = 0;


    /**
     * @描述 在onCreateView中加载布局
     */

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

             //   ListView listView = (ListView) parent;
              //  Fruit fruit = (Fruit) listView.getItemAtPosition(position);
              //  String name = fruit.getName();

                Intent intent = new Intent(getActivity(), ListActivity.class);

                Bundle bundle = new Bundle();
                bundle.putLong("id", id);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getActivity(), "id = "+id, Toast.LENGTH_SHORT).show();
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
        map.put("title", "运球");
        map.put("img", R.drawable.yunqiu);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "上篮");
        map.put("img", R.drawable.shanglan);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "投篮");
        map.put("img", R.drawable.toulan);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "进攻");
        map.put("img", R.drawable.jinggong);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "防守");
        map.put("img", R.drawable.fangshou);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "篮板");
        map.put("img", R.drawable.lanban);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "传球");
        map.put("img", R.drawable.chuanqiu);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "弹跳");
        map.put("img", R.drawable.tantiao);
        list.add(map);
        return list;


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}