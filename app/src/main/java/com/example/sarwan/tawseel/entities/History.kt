package com.example.sarwan.tawseel.entities

data class History(var id : Int = 0,
                   var date : String ? = null,
                   var orderItems : ArrayList<String> ? = null,
                   var paymentMethod : String ? = null,
                   var rating : Float ? = null,
                   var totalBill : String ? = null,
                   var yourBill : String ? = null,
                   var historyMode : HistoryMode ? = null,
                   var lat : Double = 24.966863,
                   var lon : Double = 67.048835
                   )