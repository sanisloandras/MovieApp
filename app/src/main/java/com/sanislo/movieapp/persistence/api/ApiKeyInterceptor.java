package com.sanislo.movieapp.persistence.api;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    private static final String TAG = ApiKeyInterceptor.class.getSimpleName();

    private String apiKey;

    public ApiKeyInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
