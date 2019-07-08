package com.example.sarwan.tawseel.modules.stores


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.base.BasePagerAdapter
import com.example.sarwan.tawseel.repository.BaseRepository

class StorePagerAdapter(fragment : FragmentManager, private val titlesList : List<String>, private val callBack : (StoreItemFragment) -> Unit) : BasePagerAdapter(fragment) {
    override fun getCount(): Int {
        return titlesList.size
    }

    override fun getItem(position: Int): Fragment {
        return StoreItemFragment.newInstance(position).apply(callBack)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titlesList[position]
    }
}