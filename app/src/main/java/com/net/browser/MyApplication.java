package com.net.browser;

import android.app.Application;

import org.xutils.x;

/**
 * @PackageName: com.net.browser
 * @Description: 自定义app
 * @author: LanYing
 * @date: 2016/7/18 13:42
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
