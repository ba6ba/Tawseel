package com.example.sarwan.tawseel.network

import com.example.sarwan.tawseel.BuildConfig

class NetworkConstants {
    companion object {
        private const val API_POSTFIX : String = "api/"
        val BASE_URL = "${BuildConfig.BE_URL}$API_POSTFIX"
    }
}
