package com.example.sarwan.tawseel.utils

import com.example.sarwan.tawseel.entities.DummyData

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
}