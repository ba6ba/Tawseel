package com.example.sarwan.tawseel.extensions

import android.view.View
import android.widget.LinearLayout

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

fun View.layoutParams(width : Int? = LinearLayout.LayoutParams.MATCH_PARENT, height : Int? = LinearLayout.LayoutParams.MATCH_PARENT)
        = LinearLayout.LayoutParams(width?: LinearLayout.LayoutParams.MATCH_PARENT, height?: LinearLayout.LayoutParams.MATCH_PARENT)

fun View.takeWidth() = layoutParams?.width

fun View.takeHeight() = layoutParams?.height