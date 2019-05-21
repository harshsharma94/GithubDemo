package com.waynetech.githubdemo

import com.nhaarman.mockitokotlin2.*
import com.waynetech.githubdemo.data.Repository
import com.waynetech.githubdemo.data.models.Base
import com.waynetech.githubdemo.data.models.Head
import com.waynetech.githubdemo.data.models.ReposResponse
import com.waynetech.githubdemo.data.models.User
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class PullsPresenterImplTest {

    private lateinit var presenter: PullsPresenter

    private val view: PullsView = mock()

    private val repository: Repository = mock()

    private val argumentCaptor = argumentCaptor<List<PullsAdapter.ItemType>>()

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

        presenter = PullsPresenterImpl(repository)
        presenter.setView(view)
    }

    @Test
    fun refresh() {
        val testSize = 3

        whenever(view.getOwnerName()).thenReturn(Observable.just("torvalds"))
        whenever(view.getRepoName()).thenReturn(Observable.just("linux"))

        whenever(repository.getPulls(any(), any()))
            .thenReturn(Observable.just(getReposResponse(testSize)))

        presenter.refresh()

        verify(view).getOwnerName()
        verify(view).getRepoName()

        verify(view).showLoadingView(true)
        verify(view).setItems(argumentCaptor.capture())

        assertEquals(argumentCaptor.firstValue.size, 3)
        assert(argumentCaptor.firstValue[0] is PullsAdapter.PullItem)
        assert(argumentCaptor.firstValue[1] is PullsAdapter.PullItem)
        assert(argumentCaptor.firstValue[2] is PullsAdapter.PullItem)

        verify(view).showLoadingView(false)

        verifyNoMoreInteractions(view)
    }

    @Test
    fun refresh_error() {
        whenever(view.getOwnerName()).thenReturn(Observable.just("torvalds"))
        whenever(view.getRepoName()).thenReturn(Observable.just("linux"))

        whenever(repository.getPulls(any(), any()))
            .thenReturn(Observable.error(UnknownHostException()))

        presenter.refresh()

        verify(view).getOwnerName()
        verify(view).getRepoName()

        verify(view).showLoadingView(true)
        verify(view).setItems(argumentCaptor.capture())

        assertEquals(argumentCaptor.firstValue.size, 1)
        assert(argumentCaptor.firstValue[0] is PullsAdapter.InfoItem)
    }

    private fun getReposResponse(testSize: Int): List<ReposResponse>? {
        val repos = mutableListOf<ReposResponse>()
        for (i in 0 until testSize) {
            repos.add(
                ReposResponse(
                    number = i, title = "", user = User("", ""), head =
                    Head(""), body = "", base = Base(""), html_url = ""
                )
            )
        }
        return repos
    }
}