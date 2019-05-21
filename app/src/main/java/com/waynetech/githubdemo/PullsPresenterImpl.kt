package com.waynetech.githubdemo

import com.waynetech.githubdemo.base.BasePresenterImpl
import com.waynetech.githubdemo.data.Repository
import com.waynetech.githubdemo.data.models.ReposResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PullsPresenterImpl @Inject constructor(private val repository: Repository) : BasePresenterImpl(), PullsPresenter {
    private lateinit var view: PullsView

    override fun setView(pullsView: PullsView) {
        this.view = pullsView
    }

    override fun loadData() {
        refresh()
    }

    override fun subscribe() {
        super.subscribe()
        refresh()
    }

    override fun refresh() {

        val disp = Observable.combineLatest(
            view.getOwnerName().debounce(1000, TimeUnit.MILLISECONDS),//TODO: Size > 2
            view.getRepoName().debounce(1000, TimeUnit.MILLISECONDS),
            BiFunction<CharSequence, CharSequence, Names> { ownerName, repoName ->
                Names(ownerName.toString(), repoName.toString())
            })
            .filter {
                it.ownerName.isNotEmpty() && it.repoName.isNotEmpty()
            }.switchMap {
                view.showLoadingView(true)
                repository.getPulls(it.ownerName, it.repoName)
            }.onErrorReturn {
                emptyList()
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    handleResponse(it)
                },
                { _ -> run { view.showError() } }
            )

        addDisposable(disp)
    }

    private fun handleResponse(resp: List<ReposResponse>) {
        val itemTypes = mutableListOf<PullsAdapter.ItemType>()

        itemTypes.addAll(resp.map {
            val desc =
                "#${it.number} ${it.user.login} wants to merge ${it.head.ref} into ${it.base.ref} "//TODO: Spannable and Strings.xml
            PullsAdapter.PullItem(it.user.avatar_url, it.body, desc)
        })

        view.setItems(itemTypes)
        view.showLoadingView(false)
    }

    data class Names(val ownerName: String, val repoName: String)

}