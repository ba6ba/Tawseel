package com.example.sarwan.tawseel.repository.driver

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.helper.extras.MapCameraAttributes
import com.example.sarwan.tawseel.repository.history.HistoryRepository
import com.example.sarwan.tawseel.utils.CountDownTimer

open class DriverRepository : HistoryRepository(){

    private val mapCameraAttributes = MapCameraAttributes()

    fun getDefaultCameraAttributes() = mapCameraAttributes.build()

    private var orderMessage : String = ""
    var orderMutableLiveData : MutableLiveData<Boolean> = MutableLiveData()

    private fun setObject(value: MutableLiveData<Boolean>) {
        orderMessage()
    }

    private fun orderMessage() {
        orderMessage = if (orderMutableLiveData.value == true) "Orders enabled" else "Order disabled"
    }

    fun getOrderMessage() = orderMessage

    fun requestForOrder(requestCompleted : () -> Unit) {
        Handler().postDelayed({
            requestCompleted()
        }, 2000L)
    }

    fun getOrderLiveData() = orderMutableLiveData

    private val countDownTimer : MutableLiveData<String> = MutableLiveData()

    fun initTimer() = CountDownTimer(15000,1000, countDownTimer).start()

    fun getTimer() = countDownTimer

}

