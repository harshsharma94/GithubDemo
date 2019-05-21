package com.waynetech.githubdemo

import android.view.ViewGroup
import com.waynetech.githubdemo.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_info.*

class InfoItemViewHolder(parent: ViewGroup) :
    BaseViewHolder<PullsAdapter.InfoItem>(R.layout.item_info, parent) {
    override fun bind(data: PullsAdapter.InfoItem) {

        with(data) {
            textInfo.setText(info)
        }
    }
}