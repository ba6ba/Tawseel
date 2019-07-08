package com.example.sarwan.tawseel.repository.onBoarding

import android.os.Bundle
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.Global

class SplashRepository() : BaseRepository() {

    private fun isLoggedIn() = false

    fun navigateIf(success : () -> Unit, failure : () -> Unit) = if (isLoggedIn()) success() else failure()

    val customerBundle = Bundle(1).apply { putInt(Global.PARAM, Global.CUSTOMER) }

    val companyBundle = Bundle(1).apply { putInt(Global.PARAM, Global.COMPANY) }

    val deliveryBundle = Bundle(1).apply { putInt(Global.PARAM, Global.DELIVERY) }


}