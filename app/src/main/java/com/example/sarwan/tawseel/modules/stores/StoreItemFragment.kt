package com.example.sarwan.tawseel.modules.stores

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class StoreItemFragment : BaseFragment<CustomerRepository>(R.layout.fragment_stores_items), SwipeRefreshLayout.OnRefreshListener, (Int) -> Unit {

    override fun createRepoInstance() {
        repository = getRepository(CustomerRepository::class.java)
    }


    private var swipeRefreshLayoutHelper : SwipeRefreshLayoutHelper ? = null
    private lateinit var fragmentInteraction: FragmentInteraction<Any>

    fun fragmentInteraction(listener : FragmentInteraction<Any>){
        fragmentInteraction = listener
    }

    override fun invoke(p1: Int) {
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
            layoutManager = LinearLayoutManager(getBaseActivity(),RecyclerView.VERTICAL, false)
            adapter = StoreItemAdapter(getBaseActivity(),
                repository.getStoreList(),
                this@StoreItemFragment)
            swipeRefreshLayoutHelper?.stopRefreshLoader()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(item : Int) = StoreItemFragment().apply {
            arguments  = Bundle(1).apply {
                putInt(GlobalData.PARAM, item)
            }
        }
    }
}