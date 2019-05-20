package com.waynetech.githubdemo.di

import android.app.Application
import android.content.Context
import com.waynetech.githubdemo.GithubDemoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: GithubDemoApplication) {

    @Singleton
    @Provides
    fun provideApp(): Application {
        return app
    }

    @Singleton
    @Provides
    fun context(): Context {
        return app
    }
}