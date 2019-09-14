package com.example.sarwan.tawseel.entities.requests

data class StoreRequest(
    var storeName: String? = null,
    var storeAddress: String? = null,
    var categoryId: String? = null,
    var ownerId: String? = null,
    var lat: Double? = null,
    var lng: Double? = null
)