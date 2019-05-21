package com.waynetech.githubdemo.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.waynetech.githubdemo.data.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://api.github.com")//TODO: Endpoint class by injection
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesPullsService(retrofit: Retrofit): PullsService {
        return retrofit.create(PullsService::class.java)
    }

    @Singleton
    @Provides
    fun providesRepository(repository: RepositoryImpl): Repository {
        return repository
    }

    @Singleton
    @Provides
    fun providesImageHandler(imageHandler: ImageHandlerImpl): ImageHandler {
        return imageHandler
    }
}