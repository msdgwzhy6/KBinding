package com.benny.library.kbinding.support.v7.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.benny.library.kbinding.bind.ItemViewModel
import com.benny.library.kbinding.common.adapter.AdapterItemAccessor
import com.benny.library.kbinding.view.IViewCreator

/**
 * Created by benny on 12/18/15.
 */

open class BaseRecyclerAdapter<T> (val viewCreator: IViewCreator<T>, val itemAccessor: AdapterItemAccessor<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var onItemClickListener: AdapterView.OnItemClickListener? = null

    protected class ViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var onItemClickListener: AdapterView.OnItemClickListener? = null

        init {
            itemView.setOnClickListener { onItemClickListener?.onItemClick(null, itemView, layoutPosition, 0) }
        }

        @Suppress("UNCHECKED_CAST")
        fun notifyPropertyChange(data: T?, position: Int) {
            ((itemView.tag) as? ItemViewModel<T>)?.notifyPropertyChange(data, position)
        }
    }

    protected fun createViewHolder(itemView: View) : RecyclerView.ViewHolder {
        val viewHolder:ViewHolder<T> = ViewHolder(itemView)
        viewHolder.onItemClickListener = onItemClickListener
        return viewHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return createViewHolder(viewCreator.view(parent))
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder<T>).notifyPropertyChange(itemAccessor.get(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return viewCreator.viewTypeFor(itemAccessor.get(position), position, itemCount)
    }

    override fun getItemCount(): Int {
        return itemAccessor.size()
    }
}