package com.example.sarwan.tawseel.modules.vendors

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.helper.SwipeRefreshLayoutHelper
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.repository.main.VendorRepository
import com.example.sarwan.tawseel.utils.Global
import kotlinx.android.synthetic.main.swipe_with_recycler_view.*

class VendorItemFragment : BaseFragment<VendorRepository>(R.layout.fragment_vendor_items), SwipeRefreshLayout.OnRefreshListener, (Any) -> Unit {

    private var swipeRefreshLayoutHelper : SwipeRefreshLayoutHelper? = null
    private lateinit var fragmentInteraction: FragmentInteraction<Any>

    fun fragmentInteraction(listener : FragmentInteraction<Any>){
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
            adapter = VendorItemAdapter(getBaseActivity(),
                getRepository(VendorRepository::class.java).getVendorsList(),
                this@VendorItemFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(item : Int) = VendorItemFragment().apply {
            arguments  = Bundle(1).apply {
                putInt(Global.PARAM, item)
            }
        }
    }
}