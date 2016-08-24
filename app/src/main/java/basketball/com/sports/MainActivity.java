package basketball.com.sports;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.umeng.analytics.MobclickAgent;
import com.readystatesoftware.systembartint.SystemBarTintManager;


import com.qq.e.ads.banner.BannerView;


public class MainActivity extends FragmentActivity implements OnClickListener {
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabReferee;

    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgReferee;

    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;

    private TextView title;
    BannerView bv;
    ViewGroup bannerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        bannerContainer = (ViewGroup) this.findViewById(R.id.bannerContainer);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        setSelect(0);
        initWindow();
        this.initBanner();
        this.bv.loadAD();

    }


    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.green_main_lcsq));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintResource(R.color.colorNavBar);
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    private void initBanner() {
        this.bv = new BannerView(this, ADSize.BANNER, Constants.APPID, Constants.BannerPosID);
        bv.setRefresh(30);
        bv.setADListener(new AbstractBannerADListener() {

            @Override
            public void onNoAD(int arg0) {
                Log.i("AD_DEMO", "BannerNoAD，eCode=" + arg0);
            }

            @Override
            public void onADReceiv() {
                Log.i("AD_DEMO", "ONBannerReceive");
            }
        });
        bannerContainer.addView(bv);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void initEvent() {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabReferee.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);

    }

    private void initView() {
        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabReferee = (LinearLayout) findViewById(R.id.id_tab_referee);


        mImgWeixin = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mImgFrd = (ImageButton) findViewById(R.id.id_tab_frd_img);
        mImgAddress = (ImageButton) findViewById(R.id.id_tab_address_img);
        mImgReferee = (ImageButton) findViewById(R.id.id_tab_referee_img);

        title = (TextView) findViewById(R.id.top_title);

    }

    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i) {
            case 0:
                if (mTab01 == null) {
                    mTab01 = new CategoryFragment();
                    transaction.add(R.id.id_content, mTab01);
                } else {
                    transaction.show(mTab01);
                }
                mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                title.setText("篮球教学");
                break;
            case 1:
                if (mTab02 == null) {
                    mTab02 = new AlbumFragment();
                    transaction.add(R.id.id_content, mTab02);
                } else {
                    transaction.show(mTab02);

                }
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                title.setText("经典专辑");
                break;
            case 2:
                if (mTab03 == null) {
                    mTab03 = new AboutusFragment();
                    transaction.add(R.id.id_content, mTab03);
                } else {
                    transaction.show(mTab03);
                }
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                title.setText("关于我们");
                break;
            case 3:
                if (mTab04 == null) {
                    mTab04 = new RefereeFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else {
                    transaction.show(mTab04);
                }
                mImgReferee.setImageResource(R.drawable.tab_caipan_pressed);
                title.setText("篮球裁判");
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTab01 != null) {
            transaction.hide(mTab01);
        }
        if (mTab02 != null) {
            transaction.hide(mTab02);
        }
        if (mTab03 != null) {
            transaction.hide(mTab03);
        }
        if (mTab04 != null) {
            transaction.hide(mTab04);
        }

    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.id_tab_weixin:
                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);
                break;
            case R.id.id_tab_address:
                setSelect(2);
                break;
            case R.id.id_tab_referee:
                setSelect(3);
                break;
            default:
                break;
        }
    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs() {
        mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgAddress.setImageResource(R.drawable.tab_address_normal);
        mImgReferee.setImageResource(R.drawable.tab_caipan_normal);
    }

    /**
     * Activity销毁时，销毁adView
     */
    @Override
    protected void onDestroy() {
        // adView.destroy();
        super.onDestroy();
    }

}

