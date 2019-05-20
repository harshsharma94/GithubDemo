package com.waynetech.githubdemo

import android.app.Application
import com.waynetech.githubdemo.di.AppModule
import com.waynetech.githubdemo.di.DaggerAppComponent
import com.waynetech.githubdemo.di.NetworkModule

class GithubDemoApplication : Application() {

    internal val appComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .networkModule(NetworkModule())
        .build()

    override fun onCreate() {
        super.onCreate();
        appComponent.inject(this)
    }
}
