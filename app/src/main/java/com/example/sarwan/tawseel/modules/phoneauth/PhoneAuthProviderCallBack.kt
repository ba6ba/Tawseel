package com.example.sarwan.tawseel.modules.phoneauth

interface PhoneAuthProviderCallBack {
    fun onVerificationStateResponse(phoneAuthProviderResponse: PhoneAuthProviderResponse)
}
