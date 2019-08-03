package com.example.sarwan.tawseel.helper.extras

import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class MapCameraAttributes @JvmOverloads constructor(
    private val latLng : LatLng = LatLng(GlobalData.LATITUDE, GlobalData.LONGITUDE),
    private val zoom : Float = 10f,
    private val bearing : Float = 0f,
    private val tilt : Float = 45f) {

    fun build() = CameraPosition.builder().
            target(latLng).
            zoom(zoom).
            bearing(bearing).
            tilt(tilt).
            build()
}