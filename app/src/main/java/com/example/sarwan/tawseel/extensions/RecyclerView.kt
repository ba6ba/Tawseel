package com.example.sarwan.tawseel.extensions

import androidx.recyclerview.widget.RecyclerView

val RecyclerView.Adapter<*>.emptyAdapter: Boolean
    get() {
        return itemCount == 0
    }