package com.example.sarwan.tawseel.entities.requests

data class ItemRequest(
    var itemName: String? = null,
    var storeId: String? = null,
    var itemType: String? = "new",
    var itemPrice: Int? = null,
    var itemDescription : String ? = null
)

//TODO - itemPrice should be of BigDecimal type
