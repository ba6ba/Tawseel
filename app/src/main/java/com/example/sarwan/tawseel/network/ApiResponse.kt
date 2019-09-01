package com.example.sarwan.tawseel.network

sealed class ApiResponse<T> {
    data class Loading<T>(var loading: Boolean, var message: String?) : ApiResponse<T>()
    data class Success<T>(var data: T?, var message: String?) : ApiResponse<T>()
    data class Error<T>(val message: String?) : ApiResponse<T>()

    companion object {
        fun <T> loading(isLoading: Boolean, message: String?): ApiResponse<T> =
            Loading(isLoading, message)

        fun <T> success(data: T?, message: String? = null): ApiResponse<T> = Success(data, message)

        fun <T> error(message: String?): ApiResponse<T> = Error(message)
    }
}
