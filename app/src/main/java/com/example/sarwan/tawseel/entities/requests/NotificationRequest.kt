package com.example.sarwan.tawseel.entities.requests

import java.io.Serializable

data class NotificationRequest(
    var title: String? = "",
    var description: String? = "",
    var data: Data = Data()
) : Serializable {
    data class Data(
        var orderPickupLocation: Location = Location(),
        var orderDeliverLocation: Location = Location(),
        var orderName: String? = "",
        var orderPrice: String? = "",
        var orderDescription: String? = "",
        var paymentStatus: Int = -1,
        var paymentProof: Any? = null
    )

    data class Location(
        var title: String? = "",
        var latitude: Double = 0.0,
        var longitude: Double = 0.0
    )
}