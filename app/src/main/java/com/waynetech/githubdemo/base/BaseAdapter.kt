package com.waynetech.githubdemo.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder<*>>() {

    var onClickListener: OnClickListener? = null

    internal var items: MutableList<T> = mutableListOf()

    override fun onBindViewHolder(p0: BaseViewHolder<*>, p1: Int) {
        onBind(p0, p1)

        p0.onClickListener = this.onClickListener
    }

    abstract fun onBind(p0: BaseViewHolder<*>, int: Int)

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }
}