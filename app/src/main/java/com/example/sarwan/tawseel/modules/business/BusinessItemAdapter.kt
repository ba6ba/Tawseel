package com.example.sarwan.tawseel.modules.business

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.responses.ItemListResponse
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnItemClick
import kotlinx.android.synthetic.main.vendor_list_item.view.*

class BusinessItemAdapter(
    private val context: Context, private val arrayList: ArrayList<ItemListResponse.Data>,
    private val itemClick: (Any) -> Unit
) : RecyclerView.Adapter<BusinessItemAdapter.BusinessItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessItemViewHolder {
        return BusinessItemViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.business_item_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: BusinessItemViewHolder, position: Int) {
        holder.onBindData(position)
    }

    fun addAll(itemsList: ArrayList<ItemListResponse.Data>) {
        arrayList.clear()
        arrayList.addAll(itemsList)
        notifyDataSetChanged()
    }

    inner class BusinessItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBindData(position: Int) {
            itemView.apply {
                arrayList[position].let {
                    name?.applyText(it.itemName)
                    description?.applyText(it.itemDescription)
                    price?.applyText("$ ${it.itemPrice}")
                    image?.setImageURI(it.itemImage)

                    navigateOnItemClick(position) { tag ->
                        itemClick(arrayList[tag])
                    }
                }
            }
        }
    }
}
