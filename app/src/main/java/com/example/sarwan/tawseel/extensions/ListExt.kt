package com.example.sarwan.tawseel.extensions

import com.example.sarwan.tawseel.entities.*

fun <T> ArrayList<T>.notAnEmptyList(action : (ArrayList<T>, Messages) -> Unit) = if(isNotEmpty()) action(this , noneError()) else action(this, emptyCartError())

fun <T> ArrayList<T>.validPosition(position : Int, action : (ArrayList<T>, Boolean) -> Unit) {
    action(this, validListIndex(position))
}

private fun <T> ArrayList<T>.validListIndex(position: Int) = position <= size-1