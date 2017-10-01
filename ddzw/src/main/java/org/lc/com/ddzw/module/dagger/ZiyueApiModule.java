/**
 * Copyright 2016 JustWayward Team
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lc.com.ddzw.module.dagger;

//import com.justwayward.reader.api.BookApi;
//import com.justwayward.reader.api.support.HeaderInterceptor;
//import com.justwayward.reader.api.support.Logger;
//import com.justwayward.reader.api.support.LoggingInterceptor;

import org.lc.com.ddzw.network.BookApi;
import org.lc.com.ddzw.network.ZiyueApi;
import org.lc.com.ddzw.network.support.HeaderInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

//@Module
public class ZiyueApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {

//        LoggingInterceptor logging = new LoggingInterceptor(new Logger());
//        logging.setLevel(LoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging);
        return builder.build();
    }

    @Provides
    protected BookApi provideBookService(OkHttpClient okHttpClient) {
        return BookApi.getInstance(okHttpClient);
    }

    @Provides
    protected ZiyueApi provideZiyueService(OkHttpClient okHttpClient) {
        return ZiyueApi.getInstance(okHttpClient);
    }
}