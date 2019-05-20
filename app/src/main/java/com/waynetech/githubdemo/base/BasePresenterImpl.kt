package com.waynetech.githubdemo.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenterImpl : BasePresenter {

    val compositeDisposable = CompositeDisposable()

    abstract fun loadData()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}

interface BasePresenter {
    fun subscribe()
    fun unsubscribe()
}