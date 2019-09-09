package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.modules.vendors.VendorItemAdapter
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class BusinessItemFragment : BaseFragment<BusinessRepository>(R.layout.fragment_business_items),
    SwipeRefreshLayout.OnRefreshListener, (Any) -> Unit {

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    private var swipeRefreshLayoutHelper: SwipeRefreshLayoutHelper? = null
    private lateinit var fragmentInteraction: FragmentInteraction<Any>

    fun fragmentInteraction(listener: FragmentInteraction<Any>) {
        fragmentInteraction = listener
    }

    override fun invoke(p1: Any) {
        fragmentInteraction.onFragmentShift(p1)
    }

    override fun onRefresh() {

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
            adapter = BusinessItemAdapter(
                getBaseActivity(),
                repository.getBusinessItemsList(), this@BusinessItemFragment
            )
            swipeRefreshLayoutHelper?.stopRefreshLoader()
        }
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
