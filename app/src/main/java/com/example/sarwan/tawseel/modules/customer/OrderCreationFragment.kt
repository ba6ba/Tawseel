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
import com.example.sarwan.tawseel.utils.EncodingUtils
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
        notificationRequest.data.paymentStatus = PaymentStatus.values()[position].ordinal
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
                notificationRequest.data.orderPickupLocation = NotificationRequest.Location(
                    locationResponse.formatted_address,
                    locationResponse.geometry.location.lat,
                    locationResponse.geometry.location.lng
                )
            }
        }

        order_destination?.validationResult?.foreverObserver(Observer {
            (it.data as? GooglePlacesApiResponse.Candidates)?.let { locationResponse ->
                notificationRequest.data.orderDeliverLocation = NotificationRequest.Location(
                    locationResponse.formatted_address,
                    locationResponse.geometry.location.lat,
                    locationResponse.geometry.location.lng
                )
            }
        })

        order_description?.validationResult?.foreverObserver(Observer {
            notificationRequest.data.orderDescription = it.text
            enableButtonLiveData.postValue(null)
        })

        order_title?.validationResult?.foreverObserver(Observer {
            notificationRequest.data.orderName = it.text
            enableButtonLiveData.postValue(null)
        })

        order_price?.validationResult?.foreverObserver(Observer {
            notificationRequest.data.orderPrice = it.text
            enableButtonLiveData.postValue(null)
        })

        payment_proof.validationResult.foreverObserver(Observer {
            notificationRequest.data.paymentProof = it.text
        })

        find_driver?.actionOnClick {
            createOrder()
        }

    }

    private fun createOrder() {
        val encodedString = EncodingUtils.encodeObjectToString(notificationRequest.data)
        repository.callNotificationApi(
            LocationHelper.makeLocationRequest(),
            NotificationApiRequest().apply {
                title = "Request for finding driver"
                description = "Description"
                data = NotificationApiRequest.Data().apply {
                    order = encodedString
                    user = EncodingUtils.encodeObjectToString(getProfileFromSharedPreference()?.user)
                }
            })
    }
}
