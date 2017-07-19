package com.lei.repair.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.lei.repair.R;
import com.lei.repair.base.BaseActivity;
import com.lei.repair.ui.fragment.HomeFragment;
import com.lei.repair.util.AppUtil;
import com.orhanobut.logger.Logger;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.runer.liabary.util.UiUtil;
import com.runer.liabary.widget.TabFragmentHost;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeAcitivty extends BaseActivity implements TabHost.OnTabChangeListener  {


    @InjectView(android.R.id.tabhost)
    TabFragmentHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivty);
        ButterKnife.inject(this);
        //-------------------------------蒲公英自动更新
        PgyUpdateManager.register(this,"",updateManagerListener);
        //设置底部的tab和相应的其他模块;
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setBackgroundResource(R.color.tab_color);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.addTab(mTabHost.newTabSpec("news").setIndicator(getTabItemView(R.drawable.home_home_selector, "查看最新")),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("sale_products").setIndicator(getTabItemView(R.drawable.home_home_selector, "出售商品")),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getTabItemView(R.drawable.home_home_selector, "个人中心")),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getTabItemView(R.drawable.home_home_selector, "个人中心")),
                HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator(getTabItemView(R.drawable.home_home_selector, "个人中心")),
                HomeFragment.class, null);
        mTabHost.getTabWidget().getChildAt(0).getLayoutParams().height = (int) getResources().getDimension(R.dimen.tab_height);
        mTabHost.setOnTabChangedListener(this);


    }

    @Override
    public void onTabChanged(String tabId) {
//        //如果是去往个人中心
//        if("mine".equals(tabId)){
//            if(!AppUtil.chckeLogin(this,true)){
//                UiUtil.showLongToast(this,"尚未登录，请先进行登录");
//            }
//        }
        // return;
    }
    /*create tabLayout from drawable and title*/
    private View getTabItemView(int id, String title) {
        View view = getLayoutInflater().inflate(R.layout.tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_icon);
        imageView.setImageResource(id);
        TextView textView = (TextView) view.findViewById(R.id.tab_title);
        textView.setText(title);
        return view;
    }

    //关于自动更新；
    private UpdateManagerListener updateManagerListener =new UpdateManagerListener() {
        @Override
        public void onNoUpdateAvailable() {
            Logger.d("无可用更新！");
        }
        @Override
        public void onUpdateAvailable(String s) {
            final AppBean appBean = getAppBeanFromString(s);
            new AlertDialog.Builder(HomeAcitivty.this)
                    .setTitle("版本更新")
                    .setCancelable(true)
                    .setMessage("当前版本："+ AppUtil.getVersionName(getApplicationContext())+"\n"+appBean.getReleaseNote())
                    .setNegativeButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(  DialogInterface dialog,   int which) {
                                    startDownloadTask(HomeAcitivty.this,appBean.getDownloadURL());
                                }
                            }).show();
        }
    };
    private static final long waitTime = 2000;
    private long touchTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 两次返回键，退出程序
        if (event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - touchTime) >= waitTime) {
                UiUtil.showLongToast(getApplicationContext(), "再按一次退出程序");
                touchTime = currentTime;
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid()); //获取PID
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
