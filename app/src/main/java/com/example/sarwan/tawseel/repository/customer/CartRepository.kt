package com.example.sarwan.tawseel.repository.customer

import androidx.lifecycle.MutableLiveData
import com.example.sarwan.tawseel.entities.*
import com.example.sarwan.tawseel.entities.enums.MessageType
import com.example.sarwan.tawseel.entities.enums.Section
import com.example.sarwan.tawseel.extensions.notAnEmptyList

class CartRepository : CustomerRepository() {

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