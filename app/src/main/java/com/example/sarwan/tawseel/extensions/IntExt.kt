package com.example.sarwan.tawseel.extensions

import kotlin.math.absoluteValue

fun Int?.incBy(value : Int = 1) = this?.let { absoluteValue+value }?:value

fun Int?.decBy(value : Int = 1) = this?.let { if (absoluteValue == 0) absoluteValue else absoluteValue-value }?:0

fun Int?.greaterEqualsTo(value : Int = 0) = this?.let { this>=value }?:false

fun Int?.greaterTo(value : Int = 0) = this?.let { this>value }?:false

fun Int?.lessEqualsTo(value : Int = 0) = this?.let { this<=value }?:false

fun Int?.lessTo(value : Int = 0) = this?.let { this<value }?:false