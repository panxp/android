package basketball.com.sports;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.umeng.analytics.MobclickAgent;

public class VideoActivity extends AppCompatActivity {
    private String Url = "http://player.youku.com/embed/XMTY3MTk0NDg1Mg==";

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
        MobclickAgent.onPageStart("VideoActivity"); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写。"SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        WebView webView = (WebView) findViewById(R.id.webView);
        try{
            Class.forName("android.webkit.WebView")
                    .getMethod("onPause", (Class[]) null)
                    .invoke(webView, (Object[]) null);

        }catch (Exception E){
            Log.i("closeWebView",E.getMessage());
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        String video_url = intent.getStringExtra("video_url");

        /**
         Uri uri = Uri.parse("http://www.miaopai.com/show/BwYZFrHAyQSobrFqU1jCaw__.swf");
         VideoView videoView = (VideoView)this.findViewById(R.id.video_view);
         videoView.setMediaController(new MediaController(this));
         videoView.setVideoURI(uri);
         //videoView.start();
         videoView.requestFocus();
         **/
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        if (18 < Build.VERSION.SDK_INT) {
            //18 = JellyBean MR2, KITKAT=19
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        webView.loadUrl(video_url);

    }


    /**
     * Activity销毁时，销毁adView
     */
    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            VideoActivity.this.finish();
        }
        WebView webView = (WebView) findViewById(R.id.webView);
        
        return super.onKeyDown(keyCode, event);
    }
}
