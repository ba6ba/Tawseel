package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.entities.enums.MessageType

data class Messages(var type : MessageType)

fun create(type: MessageType) = Messages(type)

fun emptyCartError() = Messages(MessageType.EMPTY_CART)

fun noInternetError() = Messages(MessageType.NO_INTERNET)

fun addMoreItemsMessage() = Messages(MessageType.ADD_ANOTHER_ITEM)

fun noneError() = Messages(MessageType.NONE)