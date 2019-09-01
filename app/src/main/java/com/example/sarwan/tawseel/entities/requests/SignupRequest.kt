package com.example.sarwan.tawseel.entities.requests

data class SignupRequest(
    var name: String,
    var phone: String,
    var email: String,
    var password: String,
    var confirmPassword: String
)