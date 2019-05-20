package com.waynetech.githubdemo.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.waynetech.githubdemo.GithubDemoApplication
import com.waynetech.githubdemo.di.ActivityComponent
import com.waynetech.githubdemo.di.ActivityModule
import com.waynetech.githubdemo.di.DaggerActivityComponent

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected fun getActivityComponent(): ActivityComponent? {
        return DaggerActivityComponent.builder()
            .appComponent((application as GithubDemoApplication).appComponent)
            .activityModule(ActivityModule())
            .build()
    }

}