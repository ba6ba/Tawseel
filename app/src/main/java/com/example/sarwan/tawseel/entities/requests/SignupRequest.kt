package com.example.sarwan.tawseel.entities.requests

data class SignupRequest(
    var name: String? = null,
    var phone: String? = null,
    var email: String? = null,
    var password: String? = null,
    var confirmPassword: String? = null,
    var userType: String? = null,
    var lat: Double? = 48.42,
    var lng: Double? = 67.23
)