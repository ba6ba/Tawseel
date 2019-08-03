package com.example.sarwan.tawseel.modules.history

import android.os.Bundle
import android.view.View
import androidx.core.view.setMargins
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.entities.Profile
import com.example.sarwan.tawseel.extensions.dpToInt
import com.example.sarwan.tawseel.extensions.layoutParams
import com.example.sarwan.tawseel.extensions.setMargin
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.repository.BaseRepository
import com.example.sarwan.tawseel.repository.history.HistoryRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class HistoryFragment : BaseFragment<BaseRepository>(R.layout.fragment_history) , () -> Unit {

    private var swipeRefreshLayoutHelper : SwipeRefreshLayoutHelper ? = null

    override fun invoke() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkForCustomization(view)
    }

    private fun checkForCustomization(view: View) {
        if (getRepository(BaseRepository::class.java).profile == Profile.DRIVER){
            reCreateView()
        }
        initViews(view)
    }

    override fun initViews(view: View?) {
        swipeRefreshLayoutHelper = SwipeRefreshLayoutHelper(view).apply {
            init()
        }

        recycler_view?.apply {
            layoutManager = LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false)
            adapter = HistoryAdapter(getBaseActivity(),
                getRepository(BaseRepository::class.java).
                    getHistoryList(getRepository(BaseRepository::class.java).mode?:HistoryMode.NON_BUSINESS),
                this@HistoryFragment)
            setRecyclerListener(MapViewRecyclerListener())
            swipeRefreshLayoutHelper?.stopRefreshLoader()
        }
    }

    private fun reCreateView() {
        val childLayout = parent_layout?.getChildAt(0)
        val childParams = childLayout?.layoutParams
        parent_layout?.removeViewAt(0)
        parent_layout?.layoutParams()?.setMargin(marginTop = resources.dpToInt(R.dimen.x80))
        parent_layout?.addView(childLayout, childParams)
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
