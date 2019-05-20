package com.waynetech.githubdemo

import com.waynetech.githubdemo.base.BasePresenterImpl
import com.waynetech.githubdemo.data.Repository
import javax.inject.Inject

class PullsPresenterImpl @Inject constructor(private val repository: Repository) : BasePresenterImpl(), PullsPresenter {
    override fun loadData() {
        refresh()
    }

    override fun refresh() {
        //TODO: repository.getPulls()
    }

}