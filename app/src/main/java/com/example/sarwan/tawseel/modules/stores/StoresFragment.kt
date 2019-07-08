package com.example.sarwan.tawseel.modules.stores

import android.os.Bundle
import android.view.View
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.interfaces.FragmentInteraction
import com.example.sarwan.tawseel.modules.vendors.VendorPagerAdapter
import com.example.sarwan.tawseel.repository.main.StoresRepository
import com.example.sarwan.tawseel.repository.main.VendorRepository
import kotlinx.android.synthetic.main.tab_layout_with_viewpager.*

class StoresFragment : BaseFragment<StoresRepository>(R.layout.fragment_stores), FragmentInteraction<Any> {

    override fun onFragmentShift(t: Any) {
        navigateTo(R.id.action_storesFragment_to_VendorFragment)
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
            adapter = storePagerAdapter()
            offscreenPageLimit = 0
        }
    }

    private fun storePagerAdapter() = StorePagerAdapter(childFragmentManager,
        titlesList = getRepository(StoresRepository::class.java).getCategoriesTitle()) {
        it.fragmentInteraction(this@StoresFragment)
    }

}