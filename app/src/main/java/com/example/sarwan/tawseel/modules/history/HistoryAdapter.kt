package com.example.sarwan.tawseel.modules.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.History
import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.visible
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.android.synthetic.main.layout_history_list_item.view.*

class HistoryAdapter(private val context: Context, private val arrayList : ArrayList<History>,
                     private val itemClick : () -> Unit) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_history_list_item, parent, false))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindData(position)
    }

    inner class HistoryViewHolder(view : View) : RecyclerView.ViewHolder(view), OnMapReadyCallback{
        public var map : GoogleMap ? = null

        init {
            itemView.map_view?.let {
                it.onCreate(null)
                it.getMapAsync(this@HistoryViewHolder)
            }
        }

        override fun onMapReady(map: GoogleMap?) {
            MapsInitializer.initialize(context)
            this.map = map
            setMapLocation()
        }

        private fun setMapLocation() {
            map?.let {
                (itemView.map_view?.tag as? History)?.also { history ->
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(history.lat, history.lon),13f))
                    it.addMarker(MarkerOptions().position(LatLng(history.lat,history.lon)))
                    it.mapType = GoogleMap.MAP_TYPE_NORMAL
                }
            }
        }

        fun bindData(position: Int){
            itemView.apply {
                arrayList[position].also {history->
                    map_view.tag = history
                    history_time?.applyText(history.date)
                    history_items?.applyText(history.orderItems)
                    if (history.historyMode == HistoryMode.BUSINESS){
                        setBusinessSection(history)
                    }
                    else {
                        setNonBusinessSection(history)
                    }
                }
            }
        }

        private fun setNonBusinessSection(history: History) {
            itemView.apply {
                non_business_history_section?.visible(true)
                payment_type?.applyText(history.paymentMethod)
                rating_bar?.rating = history.rating?:3f
                total_bill?.applyText("${total_bill?.text} ${history.totalBill}")
            }
        }

        private fun setBusinessSection(history: History) {
            itemView.apply {
                non_business_history_section?.visible(true)
                business_payment_type?.applyText(history.paymentMethod)
                history_total_bill?.applyText("${history_total_bill?.text} ${history.totalBill}")
                history_your_bill?.applyText("${history_your_bill?.text} ${history.yourBill}")
            }
        }
    }
}