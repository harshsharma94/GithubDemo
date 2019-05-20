package com.waynetech.githubdemo

import android.os.Bundle
import com.waynetech.githubdemo.base.BaseActivity
import javax.inject.Inject

class PullsActivity : BaseActivity(), PullsView {

    @Inject
    lateinit var presenter: PullsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pulls)

        getActivityComponent()?.inject(this)
    }

    override fun showLoadingView(isLoading: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
