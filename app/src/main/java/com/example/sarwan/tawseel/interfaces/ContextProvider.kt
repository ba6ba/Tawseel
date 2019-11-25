package com.example.sarwan.tawseel.interfaces

import com.example.sarwan.tawseel.base.BaseActivity

interface ContextProvider {
    fun provides(activity : BaseActivity<*>)
}
