package com.example.sarwan.tawseel.repository.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.entities.enums.GetItemType
import com.example.sarwan.tawseel.entities.enums.GetStoreType
import com.example.sarwan.tawseel.entities.requests.ItemRequest
import com.example.sarwan.tawseel.entities.requests.StoreRequest
import com.example.sarwan.tawseel.entities.responses.*
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import com.example.sarwan.tawseel.network.RetrofitCustomResponse
import com.example.sarwan.tawseel.repository.BaseRepository

class BusinessRepository : BaseRepository() {

    val DEFAULT_BUSINESS_NAME = "Business Name"
    val DEFAULT_BUSINESS_DESCRIPTION = "Business Description"

    var onEditLiveData: MutableLiveData<Boolean> = MutableLiveData()

    var businessName: MutableLiveData<String> = MutableLiveData()
    var businessDescription: MutableLiveData<String> = MutableLiveData()

    private var _categoriesListApiInstance: MediatorLiveData<ApiResponse<CategoriesListResponse>> =
        MediatorLiveData()
    var categoriesListApiInstance: MutableLiveData<ApiResponse<CategoriesListResponse>> =
        _categoriesListApiInstance

    private var _createUpdateStoreApiInstance: MediatorLiveData<ApiResponse<StoreResponse>> =
        MediatorLiveData()
    var createUpdateStoreApiInstance: MutableLiveData<ApiResponse<StoreResponse>> =
        _createUpdateStoreApiInstance

    private var _getStoresApiInstance: MediatorLiveData<ApiResponse<StoreListResponse>> =
        MediatorLiveData()
    var getStoresApiInstance: MutableLiveData<ApiResponse<StoreListResponse>> =
        _getStoresApiInstance

    private var _deleteApiInstance: MediatorLiveData<ApiResponse<DeleteResponse>> =
        MediatorLiveData()
    var deleteApiInstance: MutableLiveData<ApiResponse<DeleteResponse>> =
        _deleteApiInstance

    private var _getStoreByIdApiInstance: MediatorLiveData<ApiResponse<StoreResponse>> =
        MediatorLiveData()
    var getStoreByIdApiInstance: MutableLiveData<ApiResponse<StoreResponse>> =
        _getStoreByIdApiInstance

    private var _getStoreListByIdApiInstance: MediatorLiveData<ApiResponse<StoreListResponse>> =
        MediatorLiveData()
    var getStoreListByIdApiInstance: MutableLiveData<ApiResponse<StoreListResponse>> =
        _getStoreListByIdApiInstance

    private var _createUpdateItemApiInstance: MediatorLiveData<ApiResponse<ItemResponse>> =
        MediatorLiveData()
    var createUpdateItemApiInstance: MutableLiveData<ApiResponse<ItemResponse>> =
        _createUpdateItemApiInstance

    private var _getItemsApiInstance: MediatorLiveData<ApiResponse<ItemListResponse>> =
        MediatorLiveData()
    var getItemsApiInstance: MutableLiveData<ApiResponse<ItemListResponse>> =
        _getItemsApiInstance

    private var _getItemByIdApiInstance: MediatorLiveData<ApiResponse<ItemResponse>> =
        MediatorLiveData()
    var getItemByIdApiInstance: MutableLiveData<ApiResponse<ItemResponse>> =
        _getItemByIdApiInstance

    private var _getItemListByIdApiInstance: MediatorLiveData<ApiResponse<ItemListResponse>> =
        MediatorLiveData()
    var getItemListByIdApiInstance: MutableLiveData<ApiResponse<ItemListResponse>> =
        _getItemListByIdApiInstance

    fun callCategoriesListApi() {
        _categoriesListApiInstance.addSource(categoriesListApi()) {
            _categoriesListApiInstance.value = it
        }
    }

    private fun categoriesListApi(): LiveData<ApiResponse<CategoriesListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<CategoriesListResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().getCategories()
            .enqueue(object : RetrofitCustomResponse<CategoriesListResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callCreateStoreApi(params: StoreRequest) {
        _createUpdateStoreApiInstance.addSource(createStoreApi(params)) {
            _createUpdateStoreApiInstance.value = it
        }
    }

    private fun createStoreApi(params: StoreRequest): LiveData<ApiResponse<StoreResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<StoreResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().createStore(params)
            .enqueue(object : RetrofitCustomResponse<StoreResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callGetStoresApi() {
        _getStoresApiInstance.addSource(getStoresApi()) {
            _getStoresApiInstance.value = it
        }
    }

    private fun getStoresApi(): LiveData<ApiResponse<StoreListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<StoreListResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().getStores()
            .enqueue(object : RetrofitCustomResponse<StoreListResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callUpdateStoreApi(storeId: String, params: StoreRequest) {
        _createUpdateStoreApiInstance.addSource(updateStoreApi(storeId, params)) {
            _createUpdateStoreApiInstance.value = it
        }
    }

    private fun updateStoreApi(
        storeId: String,
        params: StoreRequest
    ): LiveData<ApiResponse<StoreResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<StoreResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().updateStore(storeId, params)
            .enqueue(object : RetrofitCustomResponse<StoreResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callDeleteStoreApi(storeId: String) {
        _deleteApiInstance.addSource(deleteStoreApi(storeId)) {
            _deleteApiInstance.value = it
        }
    }

    private fun deleteStoreApi(storeId: String): LiveData<ApiResponse<DeleteResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<DeleteResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().deleteStore(storeId)
            .enqueue(object : RetrofitCustomResponse<DeleteResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callGetStoresByIdApi(id: String, storeType: GetStoreType) {
        when (storeType) {
            GetStoreType.BY_STORE_ID -> {
                _getStoreByIdApiInstance.addSource(getStoreByIdApi(id)) {
                    _getStoreByIdApiInstance.value = it
                }
            }
            GetStoreType.BY_OWNER_ID -> {
                _getStoreListByIdApiInstance.addSource(getStoreByOwnerIdApi(id)) {
                    _getStoreListByIdApiInstance.value = it
                }
            }

            GetStoreType.BY_CATEGORY_ID -> {
                _getStoreListByIdApiInstance.addSource(getStoreByCategoryIdApi(id)) {
                    _getStoreListByIdApiInstance.value = it
                }
            }
        }
    }

    private fun getStoreByIdApi(storeId: String): LiveData<ApiResponse<StoreResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<StoreResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().findStoreById(storeId)
            .enqueue(object : RetrofitCustomResponse<StoreResponse>(responseLiveData) {})
        return responseLiveData
    }

    private fun getStoreByCategoryIdApi(categoryId: String): LiveData<ApiResponse<StoreListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<StoreListResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().findStoreByCategoryId(categoryId)
            .enqueue(object : RetrofitCustomResponse<StoreListResponse>(responseLiveData) {})
        return responseLiveData
    }

    private fun getStoreByOwnerIdApi(ownerId: String): LiveData<ApiResponse<StoreListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<StoreListResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().findStoreByOwnerId(ownerId)
            .enqueue(object : RetrofitCustomResponse<StoreListResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callCreateItemApi(params: ItemRequest) {
        _createUpdateItemApiInstance.addSource(createItemApi(params)) {
            _createUpdateItemApiInstance.value = it
        }
    }

    private fun createItemApi(params: ItemRequest): LiveData<ApiResponse<ItemResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<ItemResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().createItem(params)
            .enqueue(object : RetrofitCustomResponse<ItemResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callGetItemsApi() {
        _getItemsApiInstance.addSource(getItemsApi()) {
            _getItemsApiInstance.value = it
        }
    }

    private fun getItemsApi(): LiveData<ApiResponse<ItemListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<ItemListResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().getItems()
            .enqueue(object : RetrofitCustomResponse<ItemListResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callUpdateStoreApi(itemId: String, params: ItemRequest) {
        _createUpdateItemApiInstance.addSource(updateItemApi(itemId, params)) {
            _createUpdateItemApiInstance.value = it
        }
    }

    private fun updateItemApi(
        itemId: String,
        params: ItemRequest
    ): LiveData<ApiResponse<ItemResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<ItemResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().updateItem(itemId, params)
            .enqueue(object : RetrofitCustomResponse<ItemResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callDeleteItemApi(itemId: String) {
        _deleteApiInstance.addSource(deleteItemApi(itemId)) {
            _deleteApiInstance.value = it
        }
    }

    private fun deleteItemApi(itemId: String): LiveData<ApiResponse<DeleteResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<DeleteResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().deleteItem(itemId)
            .enqueue(object : RetrofitCustomResponse<DeleteResponse>(responseLiveData) {})
        return responseLiveData
    }

    fun callGetItemByIdApi(id: String, itemType: GetItemType) {
        when (itemType) {
            GetItemType.BY_ITEM_ID -> {
                _getItemByIdApiInstance.addSource(getItemByIdApi(id)) {
                    _getItemByIdApiInstance.value = it
                }
            }
            GetItemType.BY_ITEM_TYPE -> {
                _getItemListByIdApiInstance.addSource(getItemByItemType(id)) {
                    _getItemListByIdApiInstance.value = it
                }
            }

            GetItemType.BY_STORE_ID -> {
                _getItemListByIdApiInstance.addSource(getItemByStoreIdApi(id)) {
                    _getItemListByIdApiInstance.value = it
                }
            }
        }
    }

    private fun getItemByIdApi(itemId: String): LiveData<ApiResponse<ItemResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<ItemResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().findItemById(itemId)
            .enqueue(object : RetrofitCustomResponse<ItemResponse>(responseLiveData) {})
        return responseLiveData
    }

    private fun getItemByStoreIdApi(storeId: String): LiveData<ApiResponse<ItemListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<ItemListResponse>> =
            MutableLiveData()
        NetworkRepository.getInstance().findItemByStoreId(storeId)
            .enqueue(object : RetrofitCustomResponse<ItemListResponse>(responseLiveData) {})
        return responseLiveData
    }

    private fun getItemByItemType(type: String): LiveData<ApiResponse<ItemListResponse>> {
        val responseLiveData: MutableLiveData<ApiResponse<ItemListResponse>> = MutableLiveData()
        NetworkRepository.getInstance().findItemByItemType(type)
            .enqueue(object : RetrofitCustomResponse<ItemListResponse>(responseLiveData) {})
        return responseLiveData
    }
}