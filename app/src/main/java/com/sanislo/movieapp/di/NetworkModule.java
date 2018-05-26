package com.sanislo.movieapp.di;


import com.sanislo.movieapp.BuildConfig;
import com.sanislo.movieapp.persistence.api.ApiKeyInterceptor;
import com.sanislo.movieapp.persistence.api.MovieAppApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    MovieAppApi provideApi(OkHttpClient okHttpClient, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(MovieAppApi.class);
    }

    @Provides
    String baseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    OkHttpClient provideOkHttpClient(ApiKeyInterceptor apiKeyInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .build();
    }

    @Provides
    ApiKeyInterceptor provideApiKeyInteceptor() {
        return new ApiKeyInterceptor(BuildConfig.API_KEY);
    }
}

