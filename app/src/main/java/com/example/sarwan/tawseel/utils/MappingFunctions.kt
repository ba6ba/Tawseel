package com.example.sarwan.tawseel.utils

import com.example.sarwan.tawseel.entities.enums.ProfileType

fun getProfileTypeForApi(profileType: ProfileType?): String {
    return when (profileType) {
        ProfileType.DRIVER -> {
            "delivery"
        }
        ProfileType.CUSTOMER -> {
            "customer"
        }
        ProfileType.BUSINESS -> {
            "company"
        }
        else -> {
            ""
        }
    }
}

fun getProfileTypeForApi(profileType: String?): ProfileType {
    return when (profileType) {
        "delivery" -> {
            ProfileType.DRIVER
        }
        "customer" -> {
            ProfileType.CUSTOMER
        }
        "company" -> {
            ProfileType.BUSINESS
        }
        else -> {
            ProfileType.BUSINESS
        }
    }
}