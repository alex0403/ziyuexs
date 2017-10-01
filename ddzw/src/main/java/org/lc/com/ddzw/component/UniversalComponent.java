package org.lc.com.ddzw.component;

import org.lc.com.ddzw.views.activity.SplashActivity;

/**
 * Created by Administrator on 2017-9-19.
 */
//@Component(dependencies = AppComponent.class)
public interface UniversalComponent {

    /***
     * 加载loading图片
     */
    SplashActivity inject(SplashActivity mSplashActivity);
}
