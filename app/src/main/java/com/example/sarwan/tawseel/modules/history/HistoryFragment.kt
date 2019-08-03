package com.example.sarwan.tawseel.modules.history

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.repository.history.HistoryRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class HistoryFragment : BaseFragment<HistoryRepository>(R.layout.fragment_history) , () -> Unit {

    private var swipeRefreshLayoutHelper : SwipeRefreshLayoutHelper ? = null
    override fun invoke() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }

    override fun initViews(view: View?) {
        swipeRefreshLayoutHelper = SwipeRefreshLayoutHelper(view).apply {
            init()
        }

        recycler_view?.apply {
            layoutManager = LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false)
            adapter = HistoryAdapter(getBaseActivity(),
                getRepository(HistoryRepository::class.java).
                    getHistoryList(getRepository(HistoryRepository::class.java).mode?:HistoryMode.NON_BUSINESS),
                this@HistoryFragment)
            setRecyclerListener(MapViewRecyclerListener())
            swipeRefreshLayoutHelper?.stopRefreshLoader()
        }
    }

    companion object{

        @JvmStatic
        fun newInstance(mode : HistoryMode) = HistoryFragment().apply {
            arguments = Bundle(1).apply {
                putSerializable(GlobalData.PARAM , mode)
            }
        }
    }
}
