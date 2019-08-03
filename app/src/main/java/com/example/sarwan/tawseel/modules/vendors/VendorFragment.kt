package com.example.sarwan.tawseel.modules.vendors

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.repository.customer.CustomerRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.tab_layout_with_viewpager.*

class VendorFragment : BaseFragment<CustomerRepository>(R.layout.fragment_vendors), FragmentInteraction<Any> {

    override fun onFragmentShift(t: Any) {
        navigateTo(R.id.action_vendorFragment_to_DetailsFragment,
            bundle = Bundle(1).apply {
                putSerializable(GlobalData.PARAM, t as DummyData)
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
    }

    override fun dataToViews() {
        attachViewPager()
        tab_layout?.apply {
            setupWithViewPager(view_pager,true)
            setTabsMargin(startMargin = 30, rightMargin = 30)
        }
    }

    private fun attachViewPager() {
        view_pager?.apply {
            adapter = vendorPagerAdapter()
            offscreenPageLimit = 0
        }
    }

    private fun vendorPagerAdapter() = VendorPagerAdapter(childFragmentManager,
        titlesList = getRepository(CustomerRepository::class.java).getVendorCategoriesTitle()) {
        it.fragmentInteraction(this@VendorFragment)
    }
}