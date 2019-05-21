package com.waynetech.githubdemo

import android.view.ViewGroup
import com.waynetech.githubdemo.base.BaseAdapter
import com.waynetech.githubdemo.base.BaseViewHolder

class PullsAdapter : BaseAdapter<PullsAdapter.ItemType>() {

    fun setItems(items: List<ItemType>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_PULL -> PullItemViewHolder(parent)
            else -> throw IllegalStateException("Wrong Item Type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBind(vh: BaseViewHolder<*>, position: Int) {
        val item = getItem(position)
        when (item) {
            is PullItem -> (vh as PullItemViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    private fun getItem(pos: Int): ItemType {
        return items[pos]
    }

    interface ItemType {
        fun getType(): Int
    }

    data class PullItem(val avatarUrl: String, val title: String, val desc: String) : ItemType {
        override fun getType(): Int {
            return TYPE_PULL
        }
    }

    companion object {
        const val TYPE_PULL = 1
    }
}
