package com.example.sarwan.tawseel.modules.history

import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap

class MapViewRecyclerListener : RecyclerView.RecyclerListener {
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as? HistoryAdapter.HistoryViewHolder)?.map?.apply {
            clear()
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }
}