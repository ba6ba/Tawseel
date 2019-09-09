package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.tab_layout_with_viewpager.*

class BusinessItemsLIstingFragment :
    BaseFragment<BusinessRepository>(R.layout.fragment_business_item_listing),
    FragmentInteraction<Any> {

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
    }

    override fun onFragmentShift(t: Any) {
//        navigateTo(R.id.action_vendorFragment_to_DetailsFragment,
//            bundle = Bundle(1).apply {
//                putSerializable(GlobalData.PARAM, t as DummyData)
//            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
    }

    override fun dataToViews() {
        attachViewPager()
        tab_layout?.apply {
            setupWithViewPager(view_pager, true)
            setTabsMargin(startMargin = 30, rightMargin = 30)
        }
    }

    private fun attachViewPager() {
        view_pager?.apply {
            adapter = businessPagerAdapter()
            offscreenPageLimit = 0
        }
    }

    private fun businessPagerAdapter() = BusinessItemsPagerAdapter(
        childFragmentManager,
        titlesList = repository.getBusinessItemTitles()
    ) {
        it.fragmentInteraction(this@BusinessItemsLIstingFragment)
    }
}