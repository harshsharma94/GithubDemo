package com.waynetech.githubdemo

import com.waynetech.githubdemo.base.BasePresenter

interface PullsPresenter : BasePresenter {

    fun refresh()
    fun setView(pullsView: PullsView)//TODO: Create generic in basePresenter
}
