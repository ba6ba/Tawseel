package com.example.sarwan.tawseel.entities

import java.io.Serializable

data class DummyData(val id : Int = 0,
                     var title : String,
                     var description : String,
                     var extra : String) : Serializable