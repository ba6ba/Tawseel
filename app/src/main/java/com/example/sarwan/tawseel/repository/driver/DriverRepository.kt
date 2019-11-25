package com.example.sarwan.tawseel.repository.driver

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.base.BaseActivity
import com.example.sarwan.tawseel.entities.requests.NotificationDriverApiRequest
import com.example.sarwan.tawseel.entities.responses.GeneralResponse
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.network.RetrofitCustomResponse
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.utils.CountDownTimer

class DriverRepository() : BaseRepository(){

    var orderAccepted: Boolean = false
    var orderMutableLiveData : MutableLiveData<Boolean> = MutableLiveData()
    private var _rejectOrderApiInstance: MediatorLiveData<ApiResponse<GeneralResponse>> = MediatorLiveData()
    var rejectOrderApiInstance: MutableLiveData<ApiResponse<GeneralResponse>> = _rejectOrderApiInstance

    fun requestForOrder(requestCompleted : () -> Unit) {
        Handler().postDelayed({
            requestCompleted()
        }, 2000L)
    }

    fun getOrderLiveData() = orderMutableLiveData

    private val countDownTimer : MutableLiveData<String> = MutableLiveData()

    fun initTimer() = CountDownTimer(15000,1000, countDownTimer).start()

    fun getTimer() = countDownTimer

    fun hitDriverAvailabilityApi(apiRequest : NotificationDriverApiRequest, activity: BaseActivity<*>) {
        _rejectOrderApiInstance.addSource(driverAvailabilityApi(apiRequest, activity)) {
            _rejectOrderApiInstance.value = it
        }
    }

    private fun driverAvailabilityApi(
        apiRequest: NotificationDriverApiRequest,
        activity: BaseActivity<*>
    ) : LiveData<ApiResponse<GeneralResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<GeneralResponse>> = MutableLiveData()
        NetworkRepository.getInstance().riderAvailability(apiRequest)
            .enqueue(object : RetrofitCustomResponse<GeneralResponse>(responseLiveData, activity) {})
        return responseLiveData
    }

}

