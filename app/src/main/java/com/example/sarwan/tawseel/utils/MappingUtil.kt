package com.example.sarwan.tawseel.utils

import com.example.sarwan.tawseel.entities.enums.HistoryMode
import com.example.sarwan.tawseel.entities.enums.ProfileType

fun ProfileType.mapProfileToHistory(): HistoryMode {
    return when(this) {
        ProfileType.BUSINESS-> { HistoryMode.BUSINESS }
        else -> { HistoryMode.NON_BUSINESS }
    }
}