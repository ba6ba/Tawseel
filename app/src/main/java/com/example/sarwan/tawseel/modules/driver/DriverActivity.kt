package com.example.sarwan.tawseel.modules.driver

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.DrawerActivity
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.repository.driver.DriverRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DriverActivity : DrawerActivity<DriverRepository>(R.layout.activity_driver) , OnMapReadyCallback{

    override fun onMapReady(map: GoogleMap?) {
        configureMap(map)
    }

    private fun configureMap(map: GoogleMap?) {
        map?.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            clear()
            moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(GlobalData.LATITUDE, GlobalData.LONGITUDE),13f))
            addMarker(MarkerOptions().position(LatLng(GlobalData.LATITUDE , GlobalData.LONGITUDE)))
            /*animateCamera(CameraUpdateFactory.
                newCameraPosition(getRepository(DriverRepository::class.java).
                    getDefaultCameraAttributes()), 1000, null)*/
        }
    }

    override fun toolbarTitleChange(text: String?) {
        toolbar_title?.applyText(text)
    }

    override fun toolbarIconChange(drawable: Drawable) {
        toolbar_icon?.setImageDrawable(drawable)
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
        map?.onCreate(null)
        map?.getMapAsync(this)
    }
}
