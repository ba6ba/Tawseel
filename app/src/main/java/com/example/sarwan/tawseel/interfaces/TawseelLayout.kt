package com.example.sarwan.tawseel.interfaces

import android.view.View

interface TawseelLayout {
    fun viewListeners(){}
    fun dataToViews(){}
    fun initViews(view : View?= null) {}
    fun setObservers() {}
}