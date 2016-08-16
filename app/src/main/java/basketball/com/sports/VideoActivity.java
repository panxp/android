package basketball.com.sports;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
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
}
