package com.benny.library.kbinding.common.bindings.utils

import android.view.View
import android.widget.ListView
import com.benny.library.kbinding.common.adapter.AdapterPagingListener
import com.benny.library.kbinding.common.bindings.setPagingListener
import com.jakewharton.rxbinding.internal.MainThreadSubscription
import rx.Observable
import rx.Subscriber

/**
 * Created by benny on 12/26/15.
 */

class AdapterViewPagingOnSubscribe(val view: ListView) : Observable.OnSubscribe<Pair<Int, Any?>> {
    override fun call(subscriber: Subscriber<in Pair<Int, Any?>>) {
        val pagingListener = object : AdapterPagingListener {
            override fun onLoadPage(previous: Any?, position: Int) {
                if (subscriber.isUnsubscribed) return

                subscriber.onNext(Pair(position, previous));
            }
        }
        view.setPagingListener(pagingListener)
        subscriber.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                view.setPagingListener(null)
            }
        });
    }
}