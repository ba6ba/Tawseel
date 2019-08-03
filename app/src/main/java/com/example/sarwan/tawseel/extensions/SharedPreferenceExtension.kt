package com.example.sarwan.tawseel.extensions

import android.content.SharedPreferences

fun SharedPreferences.get(key : String) : String? = if (contains(key)) getString(key, null) else null