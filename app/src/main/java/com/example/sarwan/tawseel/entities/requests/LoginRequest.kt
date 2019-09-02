package com.example.sarwan.tawseel.entities.requests

data class LoginRequest(
    var phone: String?=null,
    var email: String?=null,
    var loginType: String?=null,
    var password: String?=null)