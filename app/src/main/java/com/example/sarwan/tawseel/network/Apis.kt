package com.example.sarwan.tawseel.network

import com.example.sarwan.tawseel.entities.requests.ItemRequest
import com.example.sarwan.tawseel.entities.requests.LoginRequest
import com.example.sarwan.tawseel.entities.requests.SignupRequest
import com.example.sarwan.tawseel.entities.requests.StoreRequest
import com.example.sarwan.tawseel.entities.responses.*
import retrofit2.Call
import retrofit2.http.*

interface Apis {

    /**
     * login
     * */
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    /**
     * signup
     * */
    @POST("signup")
    fun signup(@Body request: SignupRequest): Call<SignupResponse>

    /**
     * create store
     * */
    @POST("store")
    fun createStore(@Body request: StoreRequest): Call<StoreResponse>

    /**
     * get categories list
     * */
    @GET("category")
    fun getCategories(): Call<CategoriesListResponse>

    /**
     * get stores list
     * */
    @GET("store")
    fun getStores(): Call<StoreListResponse>

    /**
     * update store
     * */
    @PUT("store/{storeId}")
    fun updateStore(@Query("storeId") storeId: String, @Body request: StoreRequest): Call<StoreResponse>

    /**
     * delete store
     * */
    @DELETE("store/{storeId}")
    fun deleteStore(@Query("storeId") storeId: String): Call<DeleteResponse>

    /**
     * get store by store id
     * */
    @GET("store/{storeId}")
    fun findStoreById(@Query("storeId") storeId: String): Call<StoreResponse>

    /**
     * get store by category id
     * */
    @GET("store/{categoryId}")
    fun findStoreByCategoryId(@Query("categoryId") categoryId: String): Call<StoreListResponse>

    /**
     * get store by owner id
     * */
    @GET("store/{ownerId}")
    fun findStoreByOwnerId(@Query("ownerId") ownerId: String): Call<StoreListResponse>

    /**
     * create item
     * */
    @POST("item")
    fun createItem(@Body request: ItemRequest): Call<ItemResponse>

    /**
     * get item list
     * */
    @GET("item")
    fun getItems(): Call<ItemListResponse>

    /**
     * update item
     * */
    @PUT("item/{itemId}")
    fun updateItem(@Query("itemId") itemId: String, @Body request: ItemRequest): Call<ItemResponse>

    /**
     * delete item
     * */
    @DELETE("item/{itemId}")
    fun deleteItem(@Query("itemId") itemId: String): Call<DeleteResponse>

    /**
     * get item by item id
     * */
    @GET("item/{itemId}")
    fun findItemById(@Query("itemId") itemId: String): Call<ItemResponse>

    /**
     * get item by store id
     * */
    @GET("item/{storeId}")
    fun findItemByStoreId(@Query("storeId") storeId: String): Call<ItemListResponse>

    /**
     * get item by item type
     * */
    @GET("item/type/{type}")
    fun findItemByItemType(@Query("type") type: String): Call<ItemListResponse>


}