package com.example.sarwan.tawseel.modules.business

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnItemClick
import kotlinx.android.synthetic.main.vendor_list_item.view.*

class BusinessItemAdapter(
    private val context: Context, private val arrayList: ArrayList<DummyData>,
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

    inner class BusinessItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBindData(position: Int) {
            itemView.apply {
                arrayList[position].let {
                    name?.applyText(it.title)
                    description?.applyText(it.description)
                    price?.applyText("$ ${it.extra}")

                    navigateOnItemClick(position) { tag ->
                        itemClick(arrayList[tag])
                    }
                }
            }
        }
    }
}
