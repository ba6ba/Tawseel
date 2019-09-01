package com.example.sarwan.tawseel.entities.requests

data class LoginRequest(
    var phone: String,
    var email: String,
    var loginType: String,
    var password: String)