package com.example.sarwan.tawseel.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout
import android.view.ViewGroup

class TawseelTabLayout @JvmOverloads constructor(context : Context,
                                                 attributeSet: AttributeSet ? = null,
                                                 defStyleAttr : Int = 0) : TabLayout(context,attributeSet,defStyleAttr) {


    fun setTabsMargin(startMargin : Int = 0 , topMargin : Int = 0, rightMargin : Int = 0 , bottomMargin : Int = 0){
        for (i in 0 until tabCount) {
            val tab = (getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(startMargin, topMargin, rightMargin,bottomMargin)
            tab.requestLayout()
        }
    }
}