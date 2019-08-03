package com.example.sarwan.tawseel.modules.driver

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.DrawerActivity
import com.example.sarwan.tawseel.extensions.applyText
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DriverActivity : DrawerActivity(R.layout.activity_driver) , OnMapReadyCallback{

    private var mapFragment : SupportMapFragment ? = null

    override fun onMapReady(map: GoogleMap?) {
        configureMap(map)
    }








    private fun configureMap(map: GoogleMap?) {
        map?.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL

        }
    }

    override fun toolbarTitleChange(text: String?) {
        toolbar_title?.applyText(text)
    }

    override fun toolbarIconChange(drawable: Drawable) {
        toolbar_icon?.setImageDrawable(drawable)
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }
}
