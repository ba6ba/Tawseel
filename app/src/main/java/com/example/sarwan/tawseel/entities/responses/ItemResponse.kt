package com.example.sarwan.tawseel.entities.responses

data class ItemResponse(var data: Data) : GeneralResponse(){
    data class Data(
        var id: String? = null,
        var itemName: String? = null,
        var storeId: String? = null,
        var itemPrice: Int? = null,
        var itemDescription: String? = null,
        var itemImage: String? = null
    )
}
