package com.example.sarwan.tawseel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sarwan.tawseel.base.LocationActivity
import com.google.android.gms.maps.model.LatLng

class MainActivity : LocationActivity() {
    override fun onSuccess(`object`: Any) {
        saveLocationInSharedPreferences(`object` as LatLng)

    }

    override fun onFailure(error: String) {
        showMessage(error)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
