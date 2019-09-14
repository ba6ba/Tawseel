package com.example.sarwan.tawseel.entities.responses

data class CategoriesListResponse(var data: ArrayList<Data>) : GeneralResponse() {
    data class Data(
        var _id: String? = null,
        var categoryName: String? = null,
        var categoryDescription: String? = null,
        var categoryImage: String? = null,
        var createdAt: String? = null,
        var updatedAt: String? = null
    )
}
