package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.enums.GetItemType
import com.example.sarwan.tawseel.entities.responses.ItemListResponse
import com.example.sarwan.tawseel.extensions.emptyAdapter
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class BusinessItemFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_items), (Any) -> Unit {

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    private var swipeRefreshLayoutHelper: SwipeRefreshLayoutHelper? = null

    override fun invoke(obj: Any) {
        navigateTo(R.id.action_business_listing_to_details_fragment,
            bundle = Bundle(1).apply {
                putSerializable(GlobalData.PARAM, obj as ItemListResponse.Data)
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        setObservers()
        viewListeners()
    }

    override fun setObservers() {
        repository.getItemListByIdApiInstance.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiResponse.Success -> {
                    attachItemsToList(it.data?.data)
                }

                is ApiResponse.Error -> {
                    showMessage(it.message)
                }
            }
        })
    }

    private fun attachItemsToList(data: ArrayList<ItemListResponse.Data>?) {
        data?.let {
            (recycler_view?.adapter as? BusinessItemAdapter)?.addAll(data)
            swipeRefreshLayoutHelper?.apply {
                stopRefreshLoader()
                toggleLayouts(recycler_view?.adapter?.emptyAdapter == true)
            }
        }
    }

    override fun initViews(view: View?) {
        swipeRefreshLayoutHelper = SwipeRefreshLayoutHelper(view).apply {
            onRefresh { hitApi() }
        }

        recycler_view?.apply {
            layoutManager = LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false)
            adapter = BusinessItemAdapter(getBaseActivity(), ArrayList(), this@BusinessItemFragment)
        }
    }

    override fun activityCreated(savedInstanceState: Bundle?) {
        super.activityCreated(savedInstanceState)
        hitApi()
    }

    private fun hitApi() {
        repository.callGetItemByIdApi(
            getProfileFromSharedPreference()?.business?._id ?: "",
            GetItemType.BY_STORE_ID
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(item: Int) = BusinessItemFragment().apply {
            arguments = Bundle(1).apply {
                putInt(GlobalData.PARAM, item)
            }
        }
    }
}
