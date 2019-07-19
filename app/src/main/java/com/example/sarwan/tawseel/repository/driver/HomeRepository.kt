package com.example.sarwan.tawseel.repository.driver

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.repository.BaseRepository

class HomeRepository : BaseRepository() {

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
}