package com.waynetech.githubdemo

import com.waynetech.githubdemo.base.BaseView
import io.reactivex.Observable

interface PullsView : BaseView {

    fun setItems(items: List<PullsAdapter.ItemType>)
    fun getOwnerName(): Observable<CharSequence>
    fun getRepoName(): Observable<CharSequence>
    fun showError()
}