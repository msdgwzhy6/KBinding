package com.benny.library.kbinding.view

import android.view.View
import android.view.ViewGroup

/**
 * Created by benny on 11/19/15.
 */
interface IViewCreator<T> {
    fun viewTypeFor(data: T?, position: Int, itemCount: Int): Int
    fun viewTypeCount(): Int
    fun view(container: ViewGroup): View
}