package com.example.sarwan.tawseel.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng

object DistanceUtils {

    fun calculateDistanceInMeters(from : LatLng , to : LatLng) : Double {
        val floatArray  = FloatArray(2)
        Location.distanceBetween(from.latitude, from.longitude, to.latitude, to.longitude, floatArray)
        return floatArray[0].toDouble()
    }
}