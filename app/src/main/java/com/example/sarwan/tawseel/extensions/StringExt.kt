package com.example.sarwan.tawseel.extensions

import java.util.*

fun String?.isNullOrEmpty(fallBack: String): String {
    return if (isNullOrEmpty()) fallBack else this!!
}

fun String.toTitleCaseWithReplaceText(replaceCharacter : String = "_") = toLowerCase(Locale.ENGLISH).capitalize().replace(replaceCharacter, " ")