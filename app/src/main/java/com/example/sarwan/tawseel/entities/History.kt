package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.utils.GlobalData

data class History(var id : Int = 0,
                   var date : String ? = null,
                   var orderItems : ArrayList<String> ? = null,
                   var paymentMethod : String ? = null,
                   var rating : Float ? = null,
                   var totalBill : String ? = null,
                   var yourBill : String ? = null,
                   var historyMode : HistoryMode ? = null,
                   var lat : Double = GlobalData.LATITUDE,
                   var lon : Double = GlobalData.LONGITUDE
                   )