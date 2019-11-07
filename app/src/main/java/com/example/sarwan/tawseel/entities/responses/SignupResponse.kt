package com.example.sarwan.tawseel.entities.responses

data class SignupResponse(var data: Data) : GeneralResponse() {

    data class Data(
        var _id: String,
        var name: String,
        var phone: String,
        var email: String,
        var userType: String,
        var createdAt: String,
        var updatedAt: String,
        var token: String,
        var status : String
    )
}