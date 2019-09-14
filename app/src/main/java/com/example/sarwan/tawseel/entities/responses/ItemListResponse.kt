package com.example.sarwan.tawseel.entities.responses

data class ItemListResponse(var data: ArrayList<Data>) : GeneralResponse() {
    data class Data(var id: String? = null)
}
