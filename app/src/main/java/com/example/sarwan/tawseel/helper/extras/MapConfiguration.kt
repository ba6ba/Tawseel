package com.example.sarwan.tawseel.helper.extras

import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapConfiguration private constructor(){

    companion object {
        private var bldr : Builder = Builder()

        fun builder() = bldr
    }

    fun applyConfig(map : GoogleMap?) {
        map?.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            clear()
            if (bldr.moveCamera())
                animateCamera(CameraUpdateFactory.newLatLngZoom(bldr.getLatLng(), 10f))
            if (bldr.showMarker())
                addMarker(MarkerOptions().position(bldr.getLatLng()))
        }
    }

    class Builder {
        private var hasMarker : Boolean = false
        private var cameraMove : Boolean = false
        private var configLatLng : LatLng = LatLng(GlobalData.LATITUDE, GlobalData.LONGITUDE)
        private var tawseelMapConfiguration = MapConfiguration()

        fun addMarker(add : Boolean) : Builder{
            this::hasMarker.set(add)
            return this
        }

        fun moveCamera(move : Boolean) : Builder {
            this::cameraMove.set(move)
            return this
        }

        fun addLatLng(latLng: LatLng) : Builder{
            configLatLng = latLng
            return this
        }

        fun getLatLng() = configLatLng

        fun showMarker() = hasMarker

        fun moveCamera() = cameraMove

        fun build() = tawseelMapConfiguration
    }
}