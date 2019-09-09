package com.example.sarwan.tawseel.modules.history

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.enums.HistoryMode
import com.example.sarwan.tawseel.entities.enums.ProfileType
import com.example.sarwan.tawseel.extensions.dpToInt
import com.example.sarwan.tawseel.extensions.setMargin
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.repository.common.HistoryRepository
import com.example.sarwan.tawseel.utils.GlobalData
import com.example.sarwan.tawseel.utils.mapProfileToHistory
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class HistoryFragment : BaseFragment<HistoryRepository>(R.layout.fragment_history), () -> Unit {

    override fun createRepoInstance() {
        repository = getRepository(HistoryRepository::class.java)
    }

    private var swipeRefreshLayoutHelper: SwipeRefreshLayoutHelper? = null

    override fun invoke() {
        //
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
        getBaseActivity().getAppRepository().userProfile?.profileType =
            getBaseActivity().getProfileFromSharedPreference()?.profileType
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkForCustomization(view)
    }

    private fun checkForCustomization(view: View) {
        if (getBaseActivity().getAppRepository().userProfile?.profileType == ProfileType.DRIVER) {
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
            adapter = HistoryAdapter(
                getBaseActivity(),
                repository.getHistoryList(
                    getBaseActivity().getAppRepository().userProfile?.profileType?.mapProfileToHistory()
                        ?: HistoryMode.NON_BUSINESS
                ),
                this@HistoryFragment
            )
            setRecyclerListener(MapViewRecyclerListener())
            swipeRefreshLayoutHelper?.stopRefreshLoader()
        }
    }

    private fun reCreateView() {
        parent_layout?.apply {
            getChildAt(0).apply {
                (layoutParams as LinearLayout.LayoutParams).also {
                    it.setMargin(marginTop = resources.dpToInt(R.dimen.x20))
                    removeViewAt(0)
                    addView(this, it)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(mode: HistoryMode) = HistoryFragment().apply {
            arguments = Bundle(1).apply {
                putSerializable(GlobalData.PARAM, mode)
            }
        }
    }
}
