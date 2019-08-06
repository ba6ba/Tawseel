package com.example.sarwan.tawseel.entities

import com.example.sarwan.tawseel.entities.enums.Section
import java.math.BigInteger

data class Cart(var id : Int = 0,
                var title : String = "",
                var price : Long = 0,
                var section : Section = Section.Item
                )

