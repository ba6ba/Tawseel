package com.example.sarwan.tawseel.interfaces

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi

interface Resources {

    fun getStringFromValues(resId: Int) : String

    fun getColorFromValues(resId: Int) : Int

    fun getDimensionFromResources(resId: Int) : Float

    fun getIntegerFromResources(resId: Int) : Int

    fun getDrawableFromResources(@DrawableRes resId: Int) : Drawable

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFontFromResources(resId: Int) : Typeface
}