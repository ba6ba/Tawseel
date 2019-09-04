package com.example.sarwan.tawseel.entities

sealed class Validation {

    data class Result(var result: Boolean, var text: String?)

    companion object {
        fun result(result: Boolean, text: String?) = Result(result, text)
    }
}
