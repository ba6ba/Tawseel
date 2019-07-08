package com.example.sarwan.tawseel.interfaces

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface Fragment<T> {

    fun createView(inflater : LayoutInflater, layoutId : Int, container: ViewGroup?) : View? = inflater.inflate(layoutId, container, false)
    fun getRepository(t : Class<T>) : T

}