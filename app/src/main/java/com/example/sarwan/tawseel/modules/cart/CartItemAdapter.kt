package com.example.sarwan.tawseel.modules.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.Cart
import com.example.sarwan.tawseel.entities.enums.Section
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.getGST
import com.example.sarwan.tawseel.extensions.validPosition
import com.example.sarwan.tawseel.extensions.visible
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.layout_cart_list_item.view.*
import kotlinx.android.synthetic.main.layout_info_cell_with_divider.view.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class CartItemAdapter(private val context : Context, private val arrayList : ArrayList<Cart>) : RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder>() {

    private var totalPrice : Long = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        return CartItemViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_cart_list_item, parent , false))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.onBindData(position)
    }

    inner class CartItemViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun onBindData(position: Int) {
            itemView.apply {
                arrayList[position].let {
                    item?.applyText(it.title)
                    price?.applyText(it.price.toString())
                    handleDivider(divider, position)
                }
            }
        }
    }

    private fun handleDivider(divider: View?, position: Int) {
        arrayList.validPosition(position + 1){list , valid ->
            if (valid){
                divider?.visible(arrayList[position].section != arrayList[position+1].section)
            }
        }
    }

    fun addItems(arrayList: ArrayList<Cart>){
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        addPriceSections()
    }

    private fun addTotalPriceSection(){
        arrayList.add(
            Cart(id = Random(GlobalData.RANDOM_SEED_VALUE).nextInt(),
                title = context.getString(R.string.sub_total),
                section = Section.Total,
                price = totalPrice + totalPrice.getGST(10) + GlobalData.DELIVERY_CHARGES)
        )
        notifyDataSetChanged()
    }

    private fun addDeliveryItem() {
        arrayList.add(
            Cart(id = Random(GlobalData.RANDOM_SEED_VALUE).nextInt(),
                title = context.getString(R.string.delivery_charges),
                section = Section.Price,
                price = GlobalData.DELIVERY_CHARGES)
        )
    }

    private fun addGSTItem() {
        arrayList.add(
            Cart(id = Random(GlobalData.RANDOM_SEED_VALUE).nextInt(),
                title = context.getString(R.string.gst),
                section = Section.Price,
                price = totalPrice.getGST(10))
        )
    }

    private fun addTotalPriceItem() {
        arrayList.add(
            Cart(id = Random(GlobalData.RANDOM_SEED_VALUE).nextInt(),
                title = context.getString(R.string.total_price),
                section = Section.Price,
                price = totalPrice)
        )
    }

    fun addPriceSections(){
        totalPrice = arrayList.map { it.price }.sum()
        addTotalPriceItem()
        addDeliveryItem()
        addGSTItem()
        addTotalPriceSection()
    }
}