package com.example.sarwan.tawseel.extensions

import android.view.View

fun View.navigateOnClick(listener : () -> Unit) = setOnClickListener { listener() }