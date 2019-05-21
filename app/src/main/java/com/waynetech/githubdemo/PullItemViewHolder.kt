package com.waynetech.githubdemo

import android.view.ViewGroup
import com.waynetech.githubdemo.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_pull.*

class PullItemViewHolder(parent: ViewGroup) :
    BaseViewHolder<PullsAdapter.PullItem>(R.layout.item_pull, parent) {
    override fun bind(data: PullsAdapter.PullItem) {

        with(data) {
            textPullTitle.setText(title)
            textPullDesc.setText(desc)
            getImageHandler().with(avatarUrl, imageAvatar)
        }
    }
}