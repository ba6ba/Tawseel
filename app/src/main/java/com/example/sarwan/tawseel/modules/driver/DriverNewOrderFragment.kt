package com.example.sarwan.tawseel.modules.driver

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.extensions.applyText
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.extensions.visible
import com.example.sarwan.tawseel.repository.driver.DriverRepository
import com.example.sarwan.tawseel.utils.GlobalData
import kotlinx.android.synthetic.main.fragment_new_order_driver.*
import kotlinx.android.synthetic.main.layout_nearby_map_card.*

class DriverNewOrderFragment : BaseFragment<DriverRepository>(R.layout.fragment_new_order_driver) {

    override val repository: DriverRepository = getRepository(DriverRepository::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCountDownTimer()
        viewListeners()
    }

    override fun viewListeners() {
        accept?.navigateOnClick {
            navigateBack()
        }
    }

    private fun initCountDownTimer() {
        getRepository(DriverRepository::class.java).apply {
            initTimer()
            getTimer().observe(this@DriverNewOrderFragment, Observer<String> {time->
                setTimerText(time)
                accept?.visible(time != GlobalData.FINISH_TIME)
            })
        }
    }

    private fun setTimerText(text: String?) {
        remaining_time?.applyText("""${getString(R.string.remaining_time)} $text""")
    }
}