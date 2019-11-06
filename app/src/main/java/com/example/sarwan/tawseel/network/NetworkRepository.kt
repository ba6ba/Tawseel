package com.example.sarwan.tawseel.network

import com.example.sarwan.tawseel.BuildConfig
import com.example.sarwan.tawseel.interfaces.TokenProvider
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkRepository : TokenProvider {

    override fun get(token: String) {
        this.token = token
    }

    private val TIMEOUT = 25
    private var logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
    private lateinit var token: String

    init {
        setupRepo()
    }

    private fun setupRepo() {
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun getInstance() =
        Retrofit.Builder().baseUrl(NetworkConstants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create(
                gson()
            )
        ).client(httpClient(token)).build().create<Apis>(Apis::class.java)


    fun getGooglePlacesApiInstance() =
        Retrofit.Builder().baseUrl(NetworkConstants.GOOGLE_PLACES_URL).addConverterFactory(
            GsonConverterFactory.create(
                gson()
            )
        ).client(httpClient()).build().create<Apis>(Apis::class.java)


    private fun httpClient() = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val newRequest: Request
        newRequest = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }
        .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .addInterceptor(logging)
        .build()



    private fun httpClient(token: String) = OkHttpClient.Builder().addInterceptor { chain ->
        val request = chain.request()
        val newRequest: Request
        newRequest = request.newBuilder()
            .addHeader("Authorization", if (token.isEmpty()) token else "Bearer $token")
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