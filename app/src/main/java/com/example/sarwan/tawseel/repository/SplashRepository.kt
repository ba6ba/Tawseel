package com.example.sarwan.tawseel.repository

import android.content.Context

class SplashRepository(context : Context) : BaseRepository(context) {

    private fun isLoggedIn() = false

    fun navigateIf(mainResId : Int , onBoardingResId : Int) = if (isLoggedIn()) mainResId else onBoardingResId
}