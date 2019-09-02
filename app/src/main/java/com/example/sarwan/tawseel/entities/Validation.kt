package com.example.sarwan.tawseel.entities

sealed class Validation {

    data class Result(var text: String?)

    companion object {
        fun result(text: String?) = Result(text)
    }
}
