package com.example.sarwan.tawseel.utils

import java.text.SimpleDateFormat
import java.util.*

object GlobalData {
    const val FINISH_TIME: String = "00 : 00"
    const val PREFS_NAME = "CCTaskPrefs"
    const val LATLNG = "LatLng"
    const val PROFILE = "profileType"
    const val PARAM = "Param"
    const val SPLASH_DELAY = 1500L
    const val LOGIN_WITH_PHONE = 0
    const val LOGIN_WITH_EMAIL = 1
    const val CUSTOMER = 0
    const val COMPANY = 1
    const val DELIVERY = 2
    const val LATITUDE = 24.966863
    const val LONGITUDE = 67.048835
    const val RANDOM_SEED_VALUE = 999999
    const val DELIVERY_CHARGES = 20L


    fun currentTime() = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
}

const val EMPTY_STRING = ""
