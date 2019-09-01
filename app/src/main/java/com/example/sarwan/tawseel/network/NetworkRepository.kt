package com.example.sarwan.tawseel.network

import com.example.sarwan.tawseel.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkRepository {

    private val TIMEOUT = 25
    private var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()

    init {
        setupRepo()
    }

    private fun setupRepo() {
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun getInstance(token: String = "") =
        Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create(
                gson()
            )
        ).client(httpClient(token)).build().create<Apis>(Apis::class.java)


    private fun httpClient(token: String) = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val newRequest: Request
        newRequest = request.newBuilder()
            .addHeader("Authorization", token)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        chain.proceed(newRequest)
    }
        .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()


    private fun gson() = GsonBuilder().setLenient().create()
}