package com.example.sarwan.tawseel.extensions

fun String?.isNullOrEmpty(fallBack: String): String {
    return if (isNullOrEmpty()) fallBack else this!!
}