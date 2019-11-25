package com.example.sarwan.tawseel.entities.requests

import java.io.Serializable

data class NotificationDriverApiRequest(
    var isAccepted : Boolean = false,
    var customerId : String = "",
    var title: String? = "Order Rejected",
    var description: String? = "Your order has been rejected by driver",
    var data: Data = Data()
) : Serializable {
    data class Data(
        var dataobject : String = ""
    )
}