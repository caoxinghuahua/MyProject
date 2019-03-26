package com.example.caoxinghua.myapplication.okhttp;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by hua on 2019/3/16.
 */

public class MyCacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response=chain.proceed(chain.request());

        return response.newBuilder().removeHeader("pragma")
                .header("Cache-Control","max-age=60").build();
    }
}
