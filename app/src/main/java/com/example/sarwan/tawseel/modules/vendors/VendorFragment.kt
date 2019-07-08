package com.example.sarwan.tawseel.modules.vendors

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.DummyData
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.repository.main.VendorRepository
import com.example.sarwan.tawseel.utils.Global
import kotlinx.android.synthetic.main.tab_layout_with_viewpager.*

class VendorFragment : BaseFragment<VendorRepository>(R.layout.fragment_vendors), FragmentInteraction<Any> {

    override fun onFragmentShift(t: Any) {
        navigateTo(R.id.action_vendorFragment_to_DetailsFragment,
            bundle = Bundle(1).apply {
                putSerializable(Global.PARAM, t as DummyData)
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataToViews()
    }

    override fun dataToViews() {
        attachViewPager()
        tab_layout?.setupWithViewPager(view_pager,true)
    }

    private fun attachViewPager() {
        view_pager?.apply {
            adapter = vendorPagerAdapter()
            offscreenPageLimit = 0
        }
    }

    private fun vendorPagerAdapter() = VendorPagerAdapter(childFragmentManager,
        titlesList = getRepository(VendorRepository::class.java).getCategoriesTitle()) {
        it.fragmentInteraction(this@VendorFragment)
    }
}