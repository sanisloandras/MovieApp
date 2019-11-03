package com.sanislo.movieapp.di


import com.sanislo.movieapp.BuildConfig
import com.sanislo.movieapp.persistence.api.ApiKeyInterceptor
import com.sanislo.movieapp.persistence.api.MovieAppApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): MovieAppApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(MovieAppApi::class.java)
    }

    @Provides
    internal fun baseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    internal fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .build()
    }

    @Provides
    internal fun provideApiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(BuildConfig.API_KEY)
    }
}

