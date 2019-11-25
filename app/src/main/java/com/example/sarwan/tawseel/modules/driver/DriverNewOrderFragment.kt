package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.NotificationDriverApiRequest
import com.example.sarwan.tawseel.entities.requests.NotificationRequest
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.visible
import com.example.sarwan.tawseel.helper.FlowData
import com.example.sarwan.tawseel.helper.LocationHelper
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.driver.DriverRepository
import com.example.sarwan.tawseel.utils.DistanceUtils
import com.example.sarwan.tawseel.utils.EncodingUtils
import com.example.sarwan.tawseel.utils.GlobalData
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_new_order_driver.*
import kotlinx.android.synthetic.main.layout_nearby_map_card.*

class DriverNewOrderFragment : BaseFragment<DriverRepository>(R.layout.fragment_new_order_driver) {

    private var notificationRequest : NotificationRequest.Request ? = null
    private var distanceInKM : Double = 0.0
    private var pricePerKM : Int = 3
    private var apiRequest : NotificationDriverApiRequest = NotificationDriverApiRequest()

    override fun createRepoInstance() {
        repository = getRepository(DriverRepository::class.java)
    }

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCountDownTimer()
        viewListeners()
        dataToViews()
        apiRequest.customerId = notificationRequest?.user?._id!!

        repository.rejectOrderApiInstance.observe(viewLifecycleOwner, Observer {
            when(it) {
                is ApiResponse.Success -> {
                    if (repository.orderAccepted == true) {
                        handler { navigateToTrackingOrderScreen() }
                    }
                    else {
                        handler { navigateBack() }
                    }
                }

                is ApiResponse.Error -> {
                    showMessage(it.message)
                    handler { navigateBack() }
                }
            }
        })
    }

    override fun dataToViews() {
        title?.text = notificationRequest?.user?.name
        sub_title?.text = getAmountForDriver().toString()
    }

    private fun getAmountForDriver(): Double {
        val driverDistance = DistanceUtils.calculateDistanceInMeters(
            LatLng(
                getProfileFromSharedPreference()?.userLocation?.lat!!,
                getProfileFromSharedPreference()?.userLocation?.lng!!
            ),
            LatLng(
                notificationRequest?.order?.pickupLat!!,
                notificationRequest?.order?.pickupLong!!
            )
        )
        val totalDistance = driverDistance + notificationRequest?.distanceFrompickuptoDestination!!
        distanceInKM = totalDistance.div(1000)
        return calculateReqPrice()
    }

    private fun calculateReqPrice(): Double {
        return distanceInKM.times(pricePerKM)
    }

    @ExperimentalStdlibApi
    override fun activityCreated(savedInstanceState: Bundle?) {
        notificationRequest = FlowData.getData<NotificationRequest.Request>()
    }

    @ExperimentalStdlibApi
    override fun viewListeners() {
        accept?.navigateOnClick {
            hitAcceptApi()
        }

        details?.actionOnClick {
            showDetailsDialog()
        }

        reject_order?.actionOnClick {
            hitRejectApi()
        }
    }

    @ExperimentalStdlibApi
    private fun hitAcceptApi() {
        repository.orderAccepted = true
        repository.hitDriverAvailabilityApi(apiRequest.apply {
            isAccepted = true
            title = "Order Accepted"
            description = "Your order has been accepted by driver"
            data.dataobject = EncodingUtils.encodeObjectToString(NotificationRequest.Request().apply {
                user = getProfileFromSharedPreference()?.user
                req_price = calculateReqPrice().toString()
            })
        }, bActivity)
    }

    private fun navigateToTrackingOrderScreen() {
        navigateTo(R.id.actionTracking)
    }

    private fun showDetailsDialog() {
        //
    }

    private fun initCountDownTimer() {
        repository.apply {
            initTimer()
            getTimer().observe(this@DriverNewOrderFragment, Observer<String> {time->
                setTimerText(time)
                val condition = time != GlobalData.FINISH_TIME
                accept?.visible(condition)
                if (!condition) {
                    hitRejectApi()
                }
            })
        }
    }

    private fun hitRejectApi() {
        if (repository.orderAccepted != false) {
            repository.orderAccepted = false
            repository.hitDriverAvailabilityApi(apiRequest, bActivity)
        }
    }

    private fun setTimerText(text: String?) {
        remaining_time?.applyText("""${getString(R.string.remaining_time)} $text""")
    }

    private fun handler(action : () -> Unit) {
        Handler().postDelayed({
            action()
        }, 1000L)
    }
}