package com.benny.library.kbinding.support.v7.converter

import android.support.v7.widget.RecyclerView
import com.benny.library.kbinding.common.adapter.AdapterItemAccessor
import com.benny.library.kbinding.common.adapter.SimpleAdapterItemAccessor
import com.benny.library.kbinding.converter.OneWayConverter
import com.benny.library.kbinding.support.v7.adapter.BaseRecyclerAdapter
import com.benny.library.kbinding.support.v7.adapter.BaseRecyclerPagingAdapter
import com.benny.library.kbinding.view.IViewCreator

/**
 * Created by benny on 1/30/16.
 */

class ListToRecyclerAdapterConverter<T>(val viewCreator: IViewCreator<T>, val itemAccessorFactory: (List<T>) -> AdapterItemAccessor<T> = { list -> SimpleAdapterItemAccessor(list) } ) : OneWayConverter<List<T>, RecyclerView.Adapter<RecyclerView.ViewHolder>> {
    override fun convert(source: List<T>): BaseRecyclerAdapter<T> {
        return BaseRecyclerAdapter(viewCreator, itemAccessorFactory(source))
    }
}

class ListToRecyclerPagingAdapterConverter<T>(val viewCreator: IViewCreator<T>, val itemAccessorFactory: (List<T>) -> AdapterItemAccessor<T> = { list -> SimpleAdapterItemAccessor(list) } ) : OneWayConverter<List<T>, RecyclerView.Adapter<RecyclerView.ViewHolder>> {
    override fun convert(source: List<T>): BaseRecyclerPagingAdapter<T> {
        return BaseRecyclerPagingAdapter(viewCreator, itemAccessorFactory(source))
    }
}