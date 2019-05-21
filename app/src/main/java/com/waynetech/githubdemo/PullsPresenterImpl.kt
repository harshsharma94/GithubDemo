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
            view.getOwnerName().debounce(300, TimeUnit.MILLISECONDS).filter { !it.isEmpty() },//TODO: Size > 2
            view.getRepoName().debounce(300, TimeUnit.MILLISECONDS).filter { !it.isEmpty() },
            BiFunction<CharSequence, CharSequence, Names> { t1, t2 ->
                Names(t1.toString(), t2.toString())
            })
            .switchMap { repository.getPulls(it.ownerName, it.repoName) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showLoadingView(true)//TODO: Move to SwitchMap
            }
            .doOnTerminate {
                view.showLoadingView(false)//TODO: onErrorResumeNext, do not terminate
            }
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
            //val date = it.created_at
            val desc =
                "#${it.number} ${it.user.login} wants to merge ${it.head.ref} into ${it.base.ref} "//TODO: Spannable and Strings.xml
            PullsAdapter.PullItem(it.user.avatar_url, it.body, desc)
        })

        view.setItems(itemTypes)
        view.showLoadingView(false)
    }

    data class Names(val ownerName: String, val repoName: String)

}