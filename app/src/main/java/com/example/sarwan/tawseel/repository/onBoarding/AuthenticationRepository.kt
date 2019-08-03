package com.example.sarwan.tawseel.repository.onBoarding

import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.GlobalData

open class AuthenticationRepository : BaseRepository() {

    var userNameType : Int = R.string.email

    private fun isLoggedIn() = false

    fun navigateIf(success : () -> Unit, failure : () -> Unit) = if (isLoggedIn()) success() else failure()

    private var customerBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.CUSTOMER) }

    private var businessBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.COMPANY) }

    private var driverBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.DELIVERY) }

    fun getCustomerBundle(): Bundle {
        return customerBundle
    }

    fun getCompanyBundle(): Bundle {
        return businessBundle
    }

    fun getDriverBundle(): Bundle {
        return driverBundle
    }

    fun userName(type : Int?)  {
        userNameType = if (type == GlobalData.LOGIN_WITH_EMAIL) R.string.email else R.string.phone
    }

    val phoneBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.LOGIN_WITH_PHONE) }

    val emailBundle = Bundle(1).apply { putInt(GlobalData.PARAM, GlobalData.LOGIN_WITH_EMAIL) }
}