package com.example.sarwan.tawseel.utils

import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.entities.History
import com.example.sarwan.tawseel.entities.enums.HistoryMode
import com.example.sarwan.tawseel.utils.GlobalData.currentTime
import kotlin.collections.ArrayList

object DummyData {

    fun makeStoreDummyData() = ArrayList<DummyData>().apply {
        for (i in 1 until 4){
            add(DummyData(id = i, title = "Rahat Bakers",
                description = "Tipu Sultan Road, I-8 Markaz Islamabad",
                extra = "${i.times(i+10)} km "
            ))
        }
    }

    fun makeVendorDummyData() = ArrayList<DummyData>().apply {
        for (i in 1 until 4){
            add(DummyData(id = i, title = "Fresh Biscuits",
                description = "Very healthy, good for health",
                extra = "$ ${i.times(i+4)}"
            ))
        }
    }

    fun makeBusinessHistory() = ArrayList<History>().apply {
        for (i in 1 until 5){
            add(History(id = i, rating = 4f,
                orderItems = arrayListOf("Pizza", "Burger", "Cold drink"),
                totalBill = "300", yourBill = "70",
                date = currentTime() ,
                historyMode = HistoryMode.BUSINESS,
                paymentMethod = "CASH"
                ))
        }
    }

    fun makeNonBusinessHistory() = ArrayList<History>().apply {
        for (i in 1 until 5){
            add(History(id = i, rating = null,
                orderItems = arrayListOf("Order Items"),
                totalBill = "900", yourBill = null,
                date = currentTime() ,
                historyMode = HistoryMode.NON_BUSINESS,
                paymentMethod = "CASH"
            ))
        }
    }
}