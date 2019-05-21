package com.waynetech.githubdemo.di

import com.waynetech.githubdemo.GithubDemoApplication
import com.waynetech.githubdemo.data.ImageHandler
import com.waynetech.githubdemo.data.Repository
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, NetworkModule::class))
interface AppComponent {

    fun repo(): Repository

    fun imageHandler(): ImageHandler

    fun inject(app: GithubDemoApplication)
}