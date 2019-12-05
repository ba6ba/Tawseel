package com.example.sarwan.tawseel.modules.orders

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.toggleVisibility
import com.example.sarwan.tawseel.helper.extras.MapConfiguration
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.layout_nearby_map_card.*

class OrdersFragment : BaseFragment<CustomerRepository>(R.layout.fragment_order_status),
    OnMapReadyCallback {

    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }

    private var mapConfiguration: MapConfiguration? = null

    override fun onMapReady(map: GoogleMap?) {
        configureMap(map)
    }

    private fun configureMap(map: GoogleMap?) {
        mapConfiguration?.applyConfig(map)
    }

    private fun setupMap() {
        setupConfiguration()
        (childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.apply {
            onCreate(null)
            getMapAsync(this@OrdersFragment)
        }
    }

    private fun setupConfiguration() {
        mapConfiguration =
            MapConfiguration.builder().addLatLng(LatLng(GlobalData.LATITUDE, GlobalData.LONGITUDE))
                .moveCamera(true).addMarker(true).build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMap()
        dataToViews()
        viewListeners()
    }

    override fun viewListeners() {
        title?.navigateOnClick {
            navigateTo(R.id.action_ordersFragment_to_RatingFragment)
        }

        avatar?.navigateOnClick {
            navigateTo(R.id.action_ordersFragment_to_RatingFragment)
        }
    }

    override fun dataToViews() {
        repository.apply {
            title?.applyText(getDriverName())
            sub_title?.applyText(getVehicleNumber())
            rating_bar?.toggleVisibility(remaining_time)
        }
    }

}