package com.example.sarwan.tawseel.modules.driver

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.DrawerActivity
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.helper.extras.MapConfiguration
import com.example.sarwan.tawseel.repository.driver.DriverRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_driver.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DriverActivity : DrawerActivity<DriverRepository>(R.layout.activity_driver) , OnMapReadyCallback{

    override fun getNavigationMenuId(): Int = R.menu.driver_side_navigation_menu

    private var mapConfiguration : MapConfiguration ? = null

    override fun onMapReady(map: GoogleMap?) {
        configureMap(map)
    }

    private fun configureMap(map: GoogleMap?) {
        mapConfiguration?.applyConfig(map)
    }

    private fun setupMap() {
        setupConfiguration()
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.apply {
            onCreate(null)
            getMapAsync(this@DriverActivity)
        }
    }

    private fun setupConfiguration() {
        mapConfiguration =
            MapConfiguration.
                builder().
                addLatLng(LatLng(GlobalData.LATITUDE , GlobalData.LONGITUDE)).
                moveCamera(true).
                addMarker(true).
                build()
    }



    override fun toolbarTitleChange(text: String?) {
        toolbar_title?.applyText(text)
    }

    override fun toolbarIconChange(drawable: Drawable) {
        toolbar_icon?.setImageDrawable(drawable)
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
        setupMap()
    }
}
