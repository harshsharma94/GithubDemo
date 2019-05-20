package com.waynetech.githubdemo.di

import com.waynetech.githubdemo.PullsActivity
import dagger.Component

@PerActivity
@Component(modules = (arrayOf(ActivityModule::class)), dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {
    fun inject(pullsActivity: PullsActivity)
}