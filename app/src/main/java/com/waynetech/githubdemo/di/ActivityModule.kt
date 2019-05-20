package com.waynetech.githubdemo.di

import com.waynetech.githubdemo.PullsPresenter
import com.waynetech.githubdemo.PullsPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @PerActivity
    @Provides
    fun providesPullsPresenter(pullsPresenter: PullsPresenterImpl): PullsPresenter {
        return pullsPresenter
    }
}