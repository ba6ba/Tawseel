package com.example.sarwan.tawseel.entities.requests

data class NotificationRequest(val title: String, val description: String, val data: Data) {
    data class Data(val senderName: String, val recieverName: String)
}