package com.example.sarwan.tawseel.entities.requests

import java.io.Serializable

data class NotificationApiRequest(
    var title: String? = "",
    var description: String? = "",
    var data: Data = Data()
) : Serializable {
    data class Data(
        var order: String = "",
        var user: String = ""
    )
}