package com.example.sarwan.tawseel.entities.responses

data class StoreResponse(var data: Data) : GeneralResponse() {
    data class Data(
        var _id: String? = null,
        var storeName: String? = null,
        var categoryId: String? = null,
        var ownerId: String? = null,
        var storeAddress: String? = null,
        var storeDescription: String? = null,
        var storeImage: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null,
        var lat: Double? = null,
        var lng: Double? = null
    )
}