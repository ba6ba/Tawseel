package com.example.sarwan.tawseel.entities.responses

class DeleteResponse(var data: Data) : GeneralResponse() {
    data class Data(var msg: String? = null)
}
