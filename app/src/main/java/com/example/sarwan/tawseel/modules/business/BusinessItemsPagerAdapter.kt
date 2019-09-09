package com.example.sarwan.tawseel.modules.business

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sarwan.tawseel.base.BasePagerAdapter

class BusinessItemsPagerAdapter(fragment : FragmentManager,
                         private val titlesList : List<String>, private val callBack : (BusinessItemFragment) -> Unit) : BasePagerAdapter(fragment) {
    override fun getCount(): Int {
        return titlesList.size
    }

    override fun getItem(position: Int): Fragment {
        return BusinessItemFragment.newInstance(position).apply(callBack)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlesList[position]
    }
}