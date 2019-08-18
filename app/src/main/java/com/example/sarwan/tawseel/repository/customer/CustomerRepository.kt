package com.example.sarwan.tawseel.repository.customer

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.entities.Cart
import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.entities.Messages
import com.example.sarwan.tawseel.entities.addMoreItemsMessage
import com.example.sarwan.tawseel.entities.enums.MessageType
import com.example.sarwan.tawseel.entities.enums.Section
import com.example.sarwan.tawseel.entities.enums.StoresCategories
import com.example.sarwan.tawseel.entities.enums.VendorCategories
import com.example.sarwan.tawseel.extensions.decBy
import com.example.sarwan.tawseel.extensions.greaterTo
import com.example.sarwan.tawseel.extensions.incBy
import com.example.sarwan.tawseel.extensions.notAnEmptyList
import com.example.sarwan.tawseel.repository.BaseRepository

open class CustomerRepository : BaseRepository() {

    private lateinit var dummyData : DummyData

    fun fromBundle(obj : Any?) {
        dummyData = obj as DummyData
    }

    fun getData() = dummyData

    fun getDriverName() = "Kifayatullah"

    fun getVehicleNumber() = "ICT-139"

    fun getStoreCategoriesTitle() = StoresCategories.values().toList().map { it.name }

    fun getStoreList() = com.example.sarwan.tawseel.utils.DummyData.makeStoreDummyData()

    fun getVendorCategoriesTitle() = VendorCategories.values().toList().map { it.name }

    fun getVendorsList() = com.example.sarwan.tawseel.utils.DummyData.makeVendorDummyData()

    fun handleEmptyCartError() = ""

    //cart

    var cartItemListener : MutableLiveData<Int> = MutableLiveData()
    private var arrayListOfCartItems : ArrayList<Cart> = ArrayList()
    private lateinit var cartItem : Cart
    private lateinit var message : Messages
    var added : Boolean = false

    fun addToCart(dummyData: DummyData){
        cartItem = createItem(dummyData)
        arrayListOfCartItems.add(cartItem)
    }

    fun removeFromCart(dummyData: DummyData){
        arrayListOfCartItems.notAnEmptyList { list , error->
            this.message = error
            list.find { it.id == dummyData.id }?.let {cart->
                cartItem = cart
            }?:kotlin.run {
                this.message = addMoreItemsMessage()
            }
        }
        handleErrorType(message)
    }

    private fun handleErrorType(messages: Messages) {
        when(messages.type){
            MessageType.NONE -> {
                arrayListOfCartItems.remove(cartItem)
            }
            MessageType.EMPTY_CART -> {
                handleEmptyCartError()
            }
        }
    }

    private fun createItem(dummyData: DummyData) =
        Cart(id = dummyData.id, title = dummyData.title, price = dummyData.extra.toLong(), section = Section.Item)

    fun getList() = arrayListOfCartItems
}