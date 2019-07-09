package com.example.sarwan.tawseel.repository.main

import com.example.sarwan.tawseel.repository.BaseRepository

class CartRepository() : BaseRepository() {

    fun addInCart() { itemsInCart+=1 }

    fun removeFromCart() { itemsInCart-=1 }

    fun getCartValue() = itemsInCart
}