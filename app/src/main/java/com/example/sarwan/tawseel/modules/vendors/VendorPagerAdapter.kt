package com.example.sarwan.tawseel.modules.vendors


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sarwan.tawseel.base.BasePagerAdapter

class VendorPagerAdapter(fragment : FragmentManager,
                         private val titlesList : List<String>, private val callBack : (VendorItemFragment) -> Unit) : BasePagerAdapter(fragment) {
    override fun getCount(): Int {
        return titlesList.size
    }

    override fun getItem(position: Int): Fragment {
        return VendorItemFragment.newInstance(position).apply(callBack)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlesList[position]
    }
}