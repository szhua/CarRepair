package com.lei.repair.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by szhua on 2017/7/5/005.
 * github:https://github.com/szhua
 */
public class CarRepairApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

        Logger.t(Constant.TAG);
        //集成极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());

        UMShareAPI.get(this);
        Config.DEBUG = true;
        UMShareAPI.init(this, "PDLCflQT5V9N2BDS");

    }

    {
        PlatformConfig.setWeixin("wx09ae8c74b025e0eb", "9b42cad8978413a98bcebf9179a77d66");
        PlatformConfig.setSinaWeibo("1725265599", "1489dbc235e9c333b8f941ee687c8823","http://sns.whalecloud.com");
        PlatformConfig.setQQZone("1106112746", "mwCuRc8H32giOoDK");
    }
}
