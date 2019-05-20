package com.waynetech.githubdemo.di

import com.waynetech.githubdemo.GithubDemoApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, NetworkModule::class))
interface AppComponent {

    //TODO: Expose Repository
    fun inject(app: GithubDemoApplication)
}