package com.example.sarwan.tawseel.modules.stores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnItemClick
import kotlinx.android.synthetic.main.stores_list_item.view.*

class StoreItemAdapter(private val context : Context , private val arrayList : ArrayList<DummyData>,
                       private val itemClick : (Int) -> Unit
                       ) : RecyclerView.Adapter<StoreItemAdapter.StoreItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(LayoutInflater.from(context).inflate(R.layout.stores_list_item, parent , false))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        holder.onBindData(position)
    }

    inner class StoreItemViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun onBindData(position: Int) {
            itemView.apply {
                arrayList[position].let {
                    name?.applyText(it.title)
                    description?.applyText(it.description)
                    km?.applyText(it.extra)

                    navigateOnItemClick(position) { tag->
                        itemClick(arrayList[tag].id)
                    }
                }
            }
        }
    }
}