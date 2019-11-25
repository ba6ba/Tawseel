package com.example.sarwan.tawseel.utils

import java.text.SimpleDateFormat
import java.util.*

object GlobalData {
    const val FINISH_TIME: String = "00 : 00"
    const val PREFS_NAME = "CCTaskPrefs"
    const val LATLNG = "LatLng"
    const val PROFILE = "profileType"
    const val FCM_TOKEN = "fcmToken"
    const val PARAM = "Param"
    const val SPLASH_DELAY = 1500L
    const val LATITUDE = 24.966863
    const val LONGITUDE = 67.048835
    const val RANDOM_SEED_VALUE = 999999
    const val DELIVERY_CHARGES = 20L
    const val DATA_OBJECT = "dataobject"
    const val FROM_CUSTOMER = "FCM.ACTION.CUSTOMER"
    const val FROM_DRIVER = "FCM.ACTION.DRIVER"


    fun currentTime() = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Calendar.getInstance().time)
}

const val EMPTY_STRING = ""
