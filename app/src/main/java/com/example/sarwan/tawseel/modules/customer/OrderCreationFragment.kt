package com.example.sarwan.tawseel.modules.customer

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.enums.PaymentStatus
import com.example.sarwan.tawseel.entities.requests.NotificationApiRequest
import com.example.sarwan.tawseel.entities.requests.NotificationRequest
import com.example.sarwan.tawseel.entities.responses.GooglePlacesApiResponse
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.allFieldsAreValid
import com.example.sarwan.tawseel.extensions.isEnabled
import com.example.sarwan.tawseel.extensions.toTitleCaseWithReplaceText
import com.example.sarwan.tawseel.helper.LocationHelper
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.DistanceUtils
import com.example.sarwan.tawseel.utils.EncodingUtils
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_order_creation.*

@ExperimentalStdlibApi
class OrderCreationFragment : BaseFragment<CustomerRepository>(R.layout.fragment_order_creation) {

    private var enableButtonLiveData: MutableLiveData<Void> = MutableLiveData()
    private var notificationRequest = NotificationRequest()
    private lateinit var paymentStatusPicker: AlertDialog.Builder

    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPaymentStatusPicker()
        viewListeners()
        setObservers()
    }

    private fun setupPaymentStatusPicker() {
        paymentStatusPicker = AlertDialog.Builder(bActivity).apply {
            setAdapter(
                ArrayAdapter(
                    bActivity,
                    android.R.layout.select_dialog_singlechoice,
                    PaymentStatus.values().toList().map { it.name.toTitleCaseWithReplaceText() })
            ) { dialog, position ->
                onPaymentStatusSelect(position)
            }
        }
    }

    private fun onPaymentStatusSelect(position: Int) {
        payment_status?.text = PaymentStatus.values()[position].name.toTitleCaseWithReplaceText()
        notificationRequest.request.order.paymentStatus = payment_status?.text.toString()
    }

    override fun setObservers() {
        enableButtonLiveData.foreverObserver(Observer {
            find_driver?.isEnabled(
                allFieldsAreValid(
                    order_pickup_location,
                    order_destination,
                    order_description,
                    order_title,
                    order_price
                )
            )
        })

        repository.notificationApiInstance.foreverObserver(Observer {
            when (it) {
                is ApiResponse.Success -> {
                    showMessage("Request for finding driver has sent, kindly wait for driver to accept your order")
                    navigateBack()
                }

                is ApiResponse.Error -> {
                    showMessage(it.message)
                }
            }
        })
    }

    override fun viewListeners() {
        payment_status?.actionOnClick {
            paymentStatusPicker.show()
        }

        order_pickup_location?.validationResult?.observeForever {
            (it.data as? GooglePlacesApiResponse.Candidates)?.let { locationResponse ->
                notificationRequest.request.order.apply {
                    pickupAddr = locationResponse.formatted_address
                    pickupLat = locationResponse.geometry.location.lat
                    pickupLong = locationResponse.geometry.location.lng
                }
            }
        }

        order_destination?.validationResult?.foreverObserver(Observer {
            (it.data as? GooglePlacesApiResponse.Candidates)?.let { locationResponse ->
                notificationRequest.request.order.apply {
                    destinationAddr = locationResponse.formatted_address
                    destinationLat = locationResponse.geometry.location.lat
                    destinationLong = locationResponse.geometry.location.lng
                }
            }
        })

        order_description?.validationResult?.foreverObserver(Observer {
            notificationRequest.request.order.orderDescription = it.text.toString()
            enableButtonLiveData.postValue(null)
        })

        order_title?.validationResult?.foreverObserver(Observer {
            notificationRequest.request.order.orderTitle = it.text.toString()
            enableButtonLiveData.postValue(null)
        })

        order_price?.validationResult?.foreverObserver(Observer {
            notificationRequest.request.order.orderPrice = it.text.toString()
            enableButtonLiveData.postValue(null)
        })

        payment_proof.validationResult.foreverObserver(Observer {
            notificationRequest.request.order.paymentProof = it.text.toString()
        })

        find_driver?.actionOnClick {
            createOrder()
        }

    }

    private fun createOrder() {
        modifyObject()
        repository.callNotificationApi(
            LocationHelper.makeLocationRequest(),
            NotificationApiRequest().apply {
                title = "Request for finding driver"
                description = "Description"
                data.dataobject = EncodingUtils.encodeObjectToString(notificationRequest.request)
            }, bActivity)
    }

    private fun modifyObject() {
        notificationRequest.request.user = getProfileFromSharedPreference()?.user
        notificationRequest.request.distanceFrompickuptoDestination =
            DistanceUtils.calculateDistanceInMeters(
                LatLng(
                    notificationRequest.request.order.pickupLat,
                    notificationRequest.request.order.pickupLong
                ),
                LatLng(
                    notificationRequest.request.order.destinationLat,
                    notificationRequest.request.order.destinationLong
                )
            )
    }
}
