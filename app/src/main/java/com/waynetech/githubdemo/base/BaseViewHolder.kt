package com.waynetech.githubdemo.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener {

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup) : this(
        LayoutInflater.from(parent.context)
            .inflate(layoutRes, parent, false)
    )

    var onClickListener: BaseAdapter.OnClickListener? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onClickListener?.apply {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClickListener?.onClick(v!!, position)
            }
        }
    }

    abstract fun bind(data: T)

    fun getContext(): Context {
        return itemView.context
    }
}