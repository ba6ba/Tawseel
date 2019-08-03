package com.example.sarwan.tawseel.utils

import java.text.SimpleDateFormat
import java.util.*

object GlobalData {
    const val PREFS_NAME = "CCTaskPrefs"
    const val LATLNG = "LatLng"
    const val PARAM = "Param"
    const val SPLASH_DELAY = 2500L
    const val LOGIN_WITH_PHONE = 0
    const val LOGIN_WITH_EMAIL = 1
    const val CUSTOMER = 0
    const val COMPANY = 1
    const val DELIVERY = 2
    const val LATITUDE = 67.9894
    const val LONGITUDE = 60.9894


    fun currentTime() = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
}