package com.example.sarwan.tawseel.modules.phoneauth

data class PhoneAuthProviderResponse(
    val message: String,
    val type: PhoneAuthProviderResponseType,
    val code: String ? = null
)
