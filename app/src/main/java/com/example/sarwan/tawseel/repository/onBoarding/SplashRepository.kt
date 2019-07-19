package com.example.sarwan.tawseel.repository.onBoarding

import android.os.Bundle
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.Global

class SplashRepository() : BaseRepository() {

    private fun isLoggedIn() = false

    fun navigateIf(success : () -> Unit, failure : () -> Unit) = if (isLoggedIn()) success() else failure()

    private var customerBundle = Bundle(1).apply { putInt(Global.PARAM, Global.CUSTOMER) }

    private var businessBundle = Bundle(1).apply { putInt(Global.PARAM, Global.COMPANY) }

    private var driverBundle = Bundle(1).apply { putInt(Global.PARAM, Global.DELIVERY) }

    fun getCustomerBundle(): Bundle {
        return customerBundle
    }

    fun getCompanyBundle(): Bundle {
        return businessBundle
    }

    fun getDriverBundle(): Bundle {
        return driverBundle
    }
}