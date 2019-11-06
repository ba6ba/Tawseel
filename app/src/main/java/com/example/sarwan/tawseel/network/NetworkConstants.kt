package com.example.sarwan.tawseel.network

import com.example.sarwan.tawseel.BuildConfig

class NetworkConstants {
    companion object {
        private const val API_POSTFIX : String = "api/"
        val BASE_URL = "${BuildConfig.BE_URL}$API_POSTFIX"
        const val GOOGLE_PLACES_OUTPUT_TYPE = "json"
        val GOOGLE_PLACES_URL = BuildConfig.GOOGLE_PLACES
        const val GOOGLE_PLACES_API_KEY = BuildConfig.GOOGLE_PLACES_API_KEY
        const val GOOGLE_PLACES_FIELDS = "formatted_address,name,geometry/location"
    }
}
