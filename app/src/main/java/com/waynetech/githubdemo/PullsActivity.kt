package com.waynetech.githubdemo

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.waynetech.githubdemo.base.BaseActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_pulls.*
import javax.inject.Inject

class PullsActivity : BaseActivity(), PullsView {

    private val adapter = PullsAdapter()

    @Inject
    lateinit var presenter: PullsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)

        getActivityComponent()?.inject(this)

        initView()

        presenter.setView(this)
    }

    private fun initView() {
        rvPulls.apply {
            adapter = this@PullsActivity.adapter
            layoutManager = LinearLayoutManager(this@PullsActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(getDecoration(layoutManager as LinearLayoutManager))
        }
    }

    private fun getDecoration(lm: LinearLayoutManager): DividerItemDecoration {
        return DividerItemDecoration(
            this@PullsActivity,
            lm.orientation
        ).apply {
            setDrawable(
                ContextCompat.getDrawable(
                    this@PullsActivity,
                    R.drawable.drawable_divider
                )!!
            )
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showLoadingView(isLoading: Boolean) {
        runOnUiThread {
            layoutLoader.apply {
                isEnabled = isLoading
                isRefreshing = isLoading
            }
        }
    }

    override fun setItems(items: List<PullsAdapter.ItemType>) {
        adapter.setItems(items)
    }

    override fun getOwnerName(): Observable<CharSequence> {
        return RxTextView.textChanges(etOwnerName)
    }

    override fun getRepoName(): Observable<CharSequence> {
        return RxTextView.textChanges(etRepoName)
    }

    override fun showError() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();//TODO: Strings.xml
    }
}
