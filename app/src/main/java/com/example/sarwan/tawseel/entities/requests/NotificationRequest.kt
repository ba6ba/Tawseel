package com.example.sarwan.tawseel.entities.requests

import com.example.sarwan.tawseel.entities.User
import java.io.Serializable

data class NotificationRequest(
    var title: String? = "",
    var description: String? = "",
    var request: Request = Request()
) : Serializable {

    data class Request(
        var user: User? = null,
        var order: Data = Data(),
        var req_price: String = "",
        var distanceFrompickuptoDestination: Double = 0.0
    ) {
        data class Data(
            var pickupAddr: String = "",
            var pickupLat: Double = 0.0,
            var pickupLong: Double = 0.0,
            var destinationAddr: String = "",
            var destinationLat: Double = 0.0,
            var destinationLong: Double = 0.0,
            var orderTitle: String = "",
            var orderPrice: String = "",
            var paymentStatus: String = "",
            var paymentProof: String = "",
            var orderDescription: String = ""
        )
    }
}