package basketball.com.sports;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qq.e.ads.appwall.APPWall;
import com.umeng.analytics.MobclickAgent;

public class AboutusFragment extends Fragment {
    LinearLayout recommendApp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab03, container, false);
        TextView textView = (TextView) view.findViewById(R.id.aboutus);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        recommendApp = (LinearLayout) view.findViewById(R.id.recommend_app);
        recommendApp.setOnClickListener(new View.OnClickListener() {
            //为找到的button设置监听
            @Override
            //重写onClick函数
            public void onClick(View v) {
                myClickMethod(v);
            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("AboutusFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("AboutusFragment");
    }

    public void myClickMethod(View v) {
        switch (v.getId()) {
            case R.id.recommend_app:
                APPWall wall = new APPWall(getActivity(), Constants.APPID, Constants.APPWallPosID);
                wall.setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                wall.doShowAppWall();
                break;
            default:
                break;
        }
    }


}
