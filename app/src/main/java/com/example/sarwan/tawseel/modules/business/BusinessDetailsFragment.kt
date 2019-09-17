package com.example.sarwan.tawseel.modules.business

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.base.BaseFragment
import com.example.sarwan.tawseel.entities.requests.StoreRequest
import com.example.sarwan.tawseel.entities.responses.CategoriesListResponse
import com.example.sarwan.tawseel.extensions.actionOnClick
import com.example.sarwan.tawseel.extensions.navigateOnClick
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.repository.business.BusinessRepository
import kotlinx.android.synthetic.main.fragment_business_details.*

class BusinessDetailsFragment :
    BaseFragment<BusinessRepository>(R.layout.fragment_business_details) {

    private lateinit var categoryPicker: AlertDialog.Builder
    private var categoriesList: ArrayList<CategoriesListResponse.Data> = arrayListOf()
    private var enableButtonLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var storeParams: StoreRequest = StoreRequest()

    override fun createRepoInstance() {
        repository = getRepository(BusinessRepository::class.java)
        storeParams.storeName = getProfileFromSharedPreference()?.user?.name
        storeParams.ownerId = getProfileFromSharedPreference()?.user?._id
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
        viewListeners()
    }

    private fun setupPicker() {
        categoryPicker = AlertDialog.Builder(bActivity).apply {
            setAdapter(
                ArrayAdapter<String>(
                    bActivity,
                    android.R.layout.select_dialog_singlechoice,
                    categoriesList.map { it.categoryName })
            ) { p0, p1 ->
                setCategory(p1)
            }
        }
    }

    private fun setCategory(index: Int) {
        category_layout?.editText?.setText(categoriesList[index].categoryName)
        storeParams.categoryId = categoriesList[index]._id
    }

    override fun setObservers() {
        repository.categoriesListApiInstance.foreverObserver(Observer {
            if (it is ApiResponse.Success && it.data?.data?.isNullOrEmpty() == false) {
                categoriesList = it.data?.data!!
                setupPicker()
            }
        })

        category_layout?.validationResult?.foreverObserver(Observer {
            enableButtonLiveData.value = it.result
        })

        address_layout?.validationResult?.foreverObserver(Observer {
            enableButtonLiveData.value = it.result
            storeParams.storeAddress = it.text
        })

        enableButtonLiveData.foreverObserver(Observer {
            enter?.isEnabled = it
        })

        repository.createUpdateStoreApiInstance.foreverObserver(Observer {
            when(it){
                is ApiResponse.Success -> {
                    navigateTo(R.id.action_business_details_to_business_home)
                }

                is ApiResponse.Error -> {
                    showMessage(it.message)
                }
            }
        })
    }

    override fun viewListeners() {
        category_layout?.actionOnClick {
            openCategoryPicker()
        }

        enter?.navigateOnClick {
            repository.callCreateStoreApi(storeParams)
        }
    }

    private fun openCategoryPicker() {
        categoryPicker.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repository.callCategoriesListApi()
    }
}