package com.example.sarwan.tawseel.extensions

import android.view.View

fun View.navigateOnClick(listener : () -> Unit) = setOnClickListener { listener() }

fun View.actionOnClick(listener : () -> Unit) = setOnClickListener { listener() }

fun View.navigateOnItemClick(position : Int, listener : (Int) -> Unit) {
    tag = position
    setOnClickListener { listener(tag as Int) }
}

fun View.visible(boolean: Boolean) { visibility = if (boolean) View.VISIBLE else View.GONE }

fun View.toggleVisibility(viewToBeHide : View) {
    visibility = View.VISIBLE
    viewToBeHide.visibility = View.GONE
}