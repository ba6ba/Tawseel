package com.example.sarwan.tawseel.entities.responses

import com.example.sarwan.tawseel.entities.User

data class LoginResponse(var data: Data? = null) : GeneralResponse() {

    data class Data(
        var token: String? = "",
        var user: User? = null,
        var store: StoreResponse.Data? = null
    )
}
