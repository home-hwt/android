package com.home.http.retrofit;

import android.util.Log;

import java.io.IOException;
import java.io.Reader;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xuguohong on 2018/7/16.
 */

public class LogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        Response response = chain.proceed(builder.build());
//        char[] c = new char[1028 * 1028 * 8];
//        Reader reader = response.body().charStream();
//        int length = reader.read(c);

//        Log.e("message", "intercept: " + new String(c, 0, length));
//        reader.close();
        return response;
    }
}
