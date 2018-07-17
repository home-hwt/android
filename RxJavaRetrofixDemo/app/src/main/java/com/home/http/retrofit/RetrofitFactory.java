package com.home.http.retrofit;

import com.home.http.api.ServiceApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class RetrofitFactory {
    private final String mBaseUrl = "http://v.juhe.cn";

    private final long mConnectTimeOut = 30000;
    private final long mReadTimeOut = 30000;

    private RetrofitFactory(){}


    private OkHttpClient mHttpClient = new OkHttpClient.Builder()
            .connectTimeout(mConnectTimeOut,TimeUnit.MILLISECONDS)
            .readTimeout(mReadTimeOut, TimeUnit.MILLISECONDS)
            .addInterceptor(new LogInterceptor())
            .build();

    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(mBaseUrl)
            .client(mHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();



    public static RetrofitFactory getInstance(){
        return Creator.INSTANCE;
    }

    public <T> T createApi(Class<T> clazz){
        return mRetrofit.create(clazz);
    }

    public static class Creator{
        private final static RetrofitFactory INSTANCE = new RetrofitFactory();
    }
}
