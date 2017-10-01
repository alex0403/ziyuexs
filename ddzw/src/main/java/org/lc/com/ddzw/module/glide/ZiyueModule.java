package org.lc.com.ddzw.module.glide;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by Administrator on 2017-9-18.
 */

@GlideModule
public class ZiyueModule extends AppGlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setMemoryCache(new LruResourceCache(10 * 1024 * 1024));
    }


    public void registerComponents(Context context, Registry registry) {
        //registry.append(Api.GifResult.class, InputStream.class, new GiphyModelLoader.Factory());
        //registry.replace(GlideUrl.class, InputStream.class, new MultiModelLoader.Factory());
    }
}
