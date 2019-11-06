package com.example.sarwan.tawseel.entities

sealed class Validation {

    data class Result(var result: Boolean = false, var text: String? = null, var data: Any? = null)

    companion object {
        fun result(result: Boolean, text: String? = null, data: Any? = null) = Result(result, text, data)
    }
}
