package com.example.sarwan.tawseel.helper

import android.view.View
import com.example.sarwan.tawseel.extensions.visible
import kotlinx.android.synthetic.main.swipe_with_recycler_view.view.*

class SwipeRefreshLayoutHelper(private val view : View?) {

    fun init() {
        doLayoutRefresh(true)
    }

    private fun doLayoutRefresh(refresh: Boolean) {
        view?.apply {
            swipeRefreshLayout?.isRefreshing = refresh
            swipeRefreshLayoutEmpty?.isRefreshing = refresh
        }
    }

    fun stopRefreshLoader() {
        doLayoutRefresh(false)
    }

    fun toggleLayouts(show : Boolean) {
        view?.apply {
            swipeRefreshLayoutEmpty?.visible(!show)
            swipeRefreshLayout?.visible(show)
        }
    }
}