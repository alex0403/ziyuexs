package org.lc.com.ziyuexs.component;

import android.content.Context;

import org.lc.com.ziyuexs.network.ZiyueApi;

/**
 * Created by Administrator on 2017-9-19.
 */
//@Component(modules = {AppModule.class, ZiyueApiModule.class})
public interface AppComponent {
    Context getContext();

    ZiyueApi getReaderApi();
}
