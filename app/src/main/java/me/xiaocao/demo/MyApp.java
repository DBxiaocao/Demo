package me.xiaocao.demo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * description: MyApp
 * author: xiaocao
 * date: 17/10/19 17:49
 */

public class MyApp extends Application{
    public static MyApp mInstance;

    public static MyApp getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        SDKInitializer.initialize(this);
    }
}
