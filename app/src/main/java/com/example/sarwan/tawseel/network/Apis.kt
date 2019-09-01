package com.example.sarwan.tawseel.network

import com.example.sarwan.tawseel.entities.requests.LoginRequest
import com.example.sarwan.tawseel.entities.requests.SignupRequest
import com.example.sarwan.tawseel.entities.responses.LoginResponse
import com.example.sarwan.tawseel.entities.responses.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Apis {

    /**
     * login
     * */
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    /**
     * signup
     * */
    @POST("signup")
    fun signup(@Body request: SignupRequest): Call<SignupResponse>
}