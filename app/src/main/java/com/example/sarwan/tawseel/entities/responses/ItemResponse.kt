package com.example.sarwan.tawseel.entities.responses

data class ItemResponse(var data: Data) : GeneralResponse() {
    data class Data(var id: String? = null)
}
