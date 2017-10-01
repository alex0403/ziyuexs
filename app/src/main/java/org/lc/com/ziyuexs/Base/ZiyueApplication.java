package org.lc.com.ziyuexs.Base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatDelegate;

import org.lc.com.ziyuelibary.utils.AppUtils;
import org.lc.com.ziyuelibary.utils.LogUtils;
import org.lc.com.ziyuelibary.utils.SharedPreferencesUtil;
import org.lc.com.ziyuexs.component.AppComponent;

import java.io.File;

//import org.lc.com.ziyuexs.component.DaggerAppComponent;

/**
 * Created by Administrator on 2017-9-13.
 */

public class ZiyueApplication extends Application {

    private static ZiyueApplication mAplication;
    private static AppComponent mAppComponent;



    @Override
    public void onCreate(){
        super.onCreate();
        mAplication = (ZiyueApplication) this.getApplicationContext();
        AppUtils.init(this);
        LogUtils.init(this, Environment.getExternalStorageDirectory().getPath() + File.separator + this.getPackageName());
        initPrefs();
        initNightMode();

        //initCompoent();
    }
    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.Theme.ISNIGHT, false);
        LogUtils.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }



    private void initCompoent() {
//        mAppComponent = DaggerAppComponent.builder()
//                .ziyueApiModule(new ZiyueApiModule())
//                .appModule(new AppModule(this))
//                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static ZiyueApplication getsInstance() {
        return mAplication;
    }
}
