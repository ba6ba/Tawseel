package com.example.sarwan.tawseel.helper

import android.view.View
import com.example.sarwan.tawseel.extensions.visible
import kotlinx.android.synthetic.main.swipe_with_recycler_view.view.*

class SwipeRefreshLayoutHelper(private val view : View?) {

    init {
        doLayoutRefresh(true)
    }

    private fun doLayoutRefresh(refresh: Boolean) {
        view?.apply {
            swipeRefreshLayout?.isRefreshing = refresh
            swipeRefreshLayoutEmpty?.isRefreshing = refresh
        }
    }

    fun onRefresh(invoke : () -> Unit) {
        view?.swipeRefreshLayout?.setOnRefreshListener { invoke() }
    }

    fun stopRefreshLoader() {
        doLayoutRefresh(false)
    }

    fun toggleLayouts(show : Boolean) {
        view?.apply {
            swipeRefreshLayoutEmpty?.visible(show)
            swipeRefreshLayout?.visible(!show)
        }
    }
}