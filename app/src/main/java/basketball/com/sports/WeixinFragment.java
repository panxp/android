package basketball.com.sports;

import android.app.Activity;
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

public class WeixinFragment extends Fragment {

    private ListView listView;
   // private ArrayList<String> list = new ArrayList<String>();
   private List<Map<String, Object>> list;
    private int i = 0;

    /**
    @描述
    在onCreateView中加载布局
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tab01, container, false);
        listView = (ListView) view.findViewById(R.id.list);



        SimpleAdapter adapter = new SimpleAdapter(getActivity(),getData(),R.layout.list_item,
                new String[]{"title","info","img"},
                new int[]{R.id.title,R.id.info,R.id.img}
        );
        listView.setAdapter(adapter);




   //     String[] data = new String[] { "java", "C++", "JavaScript", "Php",
       //         "Python" };
     //   ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
      //  listView.setAdapter(arrayAdapter);
        //   Log.i(data);
        // return inflater.inflate(R.layout.tab01, container, false);
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
        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.ic_launcher);
        list.add(map);

        return list;
    }

}
