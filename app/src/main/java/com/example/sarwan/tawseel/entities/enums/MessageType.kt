package com.example.sarwan.tawseel.entities.enums

enum class MessageType(private val message : String) {
    NO_INTERNET("No Internet Connection"),
    EMPTY_CART("Add an item to Cart"),
    ADD_ANOTHER_ITEM("Do you want to add more items?"),
    NONE("");

    fun message() = message
}