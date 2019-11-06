package com.example.sarwan.tawseel.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.tawseel.R
import com.example.sarwan.tawseel.entities.AutoCompleteModel
import com.example.sarwan.tawseel.entities.Validation
import com.example.sarwan.tawseel.entities.enums.Irrelevant
import com.example.sarwan.tawseel.entities.responses.GooglePlacesApiResponse
import com.example.sarwan.tawseel.extensions.*
import com.example.sarwan.tawseel.network.ApiResponse
import com.example.sarwan.tawseel.network.NetworkRepository
import kotlinx.android.synthetic.main.google_places_auto_complete_list_item.view.*
import kotlinx.android.synthetic.main.layout_auto_complete_google_places.view.*
import retrofit2.Call
import retrofit2.Response

class TawseelGooglePlacesAutoCompleteLayout @JvmOverloads constructor(
    context: Context,
    private var attrs: AttributeSet? = null,
    private var defStyleAttr: Int = 0
) : BaseTawseelInputLayout(context, attrs, defStyleAttr), (AutoCompleteModel) -> Unit {

    private var mAutoCompleteLayout: Int = -1
    private var shouldUseGooglePlacesApi: Boolean = true
    private var autoCompleteList: ArrayList<AutoCompleteModel> = ArrayList()
    private var placesList: ArrayList<GooglePlacesApiResponse.Candidates>? = ArrayList()

    private val googlePlacesApiResponse: MutableLiveData<ApiResponse<GooglePlacesApiResponse>> =
        MutableLiveData()

    init {
        initialize()
    }

    private fun initialize() {
        View.inflate(context, R.layout.layout_auto_complete_google_places, this)
        getAttributes()
        updateAttributes()
        setObservers()
    }

    override fun invoke(p1: AutoCompleteModel) {
        edit_text?.setText(p1.title)
        auto_complete_rv?.visible(false)
        validationResult.postValue(
            Validation.result(
                true,
                data = placesList?.find { it.id == p1.id })
        )
    }


    private fun setObservers() {
        googlePlacesApiResponse.observeForever {
            when (it) {
                is ApiResponse.Success -> {
                    placesList = it.data?.results
                    autoCompleteList.clear()
                    if (placesList?.isEmpty() == true) {
                        auto_complete_rv?.visible(false)
                    } else {
                        it.data?.results?.forEach { data ->
                            autoCompleteList.add(
                                AutoCompleteModel(
                                    data.name,
                                    data.formatted_address,
                                    data.id
                                )
                            )
                        }
                        setAdapter(autoCompleteList)
                    }
                }

                is ApiResponse.Error -> {
                    validationResult.postValue(Validation.result(false, it.message))
                }
            }
        }
    }

    private fun setAdapter(arrayList: ArrayList<AutoCompleteModel>) {
        auto_complete_rv?.apply {
            adapter = GooglePlacesRecyclerViewAdapter(
                context,
                arrayList,
                this@TawseelGooglePlacesAutoCompleteLayout
            )
            adapter?.notifyDataSetChanged()
        }
    }

    private fun updateAttributes() {
        setIsViewEnabled()
        setHint()
        addTextChangeListener()
    }

    private fun setIsViewEnabled() {
        _layout?.editText?.isEnabled = isViewEnabled
    }

    fun setHint(hint: String? = mHint) {
        mHint = hint
        _layout?.hint(mHint ?: "")
    }

    private fun addTextChangeListener() {
        _layout?.textChangeListener(3) {
            when (it) {
                is String -> {
                    if (shouldUseGooglePlacesApi) {
                        auto_complete_rv?.visible(true)
                        hitGooglePlacesApi(it)
                    } else {
                        // configure any other third party library
                    }
                }

                is Irrelevant -> {
                    auto_complete_rv?.visible(false)
                }
            }
        }
    }

    private fun hitGooglePlacesApi(it: String?) {
        NetworkRepository.getGooglePlacesApiInstance().places(it)
            .enqueue(object :
                retrofit2.Callback<GooglePlacesApiResponse> {
                override fun onFailure(call: Call<GooglePlacesApiResponse>, t: Throwable) {
                    googlePlacesApiResponse.postValue(ApiResponse.error(t.localizedMessage))
                }

                override fun onResponse(
                    call: Call<GooglePlacesApiResponse>,
                    response: Response<GooglePlacesApiResponse>
                ) {
                    googlePlacesApiResponse.postValue(ApiResponse.success(response.body()))
                }
            })
    }


    private fun getAttributes() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TawseelGooglePlacesAutoCompleteLayout,
            0,
            0
        ).apply {
            try {
                mHint = getString(R.styleable.TawseelGooglePlacesAutoCompleteLayout_hint_text)
                isViewEnabled = getBoolean(
                    R.styleable.TawseelGooglePlacesAutoCompleteLayout_view_enabled,
                    true
                )
                shouldUseGooglePlacesApi = getBoolean(
                    R.styleable.TawseelGooglePlacesAutoCompleteLayout_use_google_places_api,
                    true
                )
                mAutoCompleteLayout = getResourceId(
                    R.styleable.TawseelGooglePlacesAutoCompleteLayout_auto_complete_layout,
                    android.R.layout.select_dialog_item
                )
            } finally {
                recycle()
            }
        }
    }

    class GooglePlacesRecyclerViewAdapter(
        private val context: Context,
        private val arrayList: ArrayList<AutoCompleteModel>,
        private val listener: (AutoCompleteModel) -> Unit
    ) : RecyclerView.Adapter<GooglePlacesRecyclerViewAdapter.GooglePlacesRecyclerViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): GooglePlacesRecyclerViewHolder {
            return GooglePlacesRecyclerViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.google_places_auto_complete_list_item,
                    parent,
                    false
                )
            )
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        override fun onBindViewHolder(holder: GooglePlacesRecyclerViewHolder, position: Int) {
            holder.onBind(position)
        }

        inner class GooglePlacesRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun onBind(position: Int) {
                itemView.apply {
                    tag = position
                    title?.applyText(arrayList[position].title)
                    description?.applyText(arrayList[position].description)
                    actionOnClick {
                        listener(arrayList[tag as Int])
                    }
                }
            }
        }
    }
}