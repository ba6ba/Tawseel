package com.example.sarwan.tawseel.modules.orders

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.toggleVisibility
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_order_status.*
import kotlinx.android.synthetic.main.layout_nearby_map_card.*

class OrdersFragment : BaseFragment<CustomerRepository>(R.layout.fragment_order_status), OnMapReadyCallback {
    private var mapFragment : SupportMapFragment ? = null
    override fun onMapReady(map: GoogleMap?) {
        configureMap(map)
    }

    private fun configureMap(map: GoogleMap?) {
        map?.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            clear()
            moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(GlobalData.LATITUDE, GlobalData.LONGITUDE),13f))
            addMarker(MarkerOptions().position(LatLng(GlobalData.LATITUDE , GlobalData.LONGITUDE)))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMap()
        dataToViews()
        viewListeners()
    }

    private fun setupMap() {
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.onCreate(null)
        mapFragment?.getMapAsync(this)
    }

    override fun viewListeners() {
        title?.navigateOnClick {
            navigateTo(R.id.action_ordersFragment_to_RatingFragment)
        }
    }

    override fun dataToViews() {
        getRepository(CustomerRepository::class.java).apply {
            title?.applyText(getDriverName())
            sub_title?.applyText(getVehicleNumber())
            rating_bar?.toggleVisibility(remaining_time)
        }
    }

}