package com.example.sarwan.tawseel.utils

import com.example.sarwan.tawseel.entities.enums.HistoryMode
import com.example.sarwan.tawseel.entities.enums.Profile

fun Profile.mapProfileToHistory(): HistoryMode {
    return when(this) {
        Profile.BUSINESS-> { HistoryMode.BUSINESS }
        else -> { HistoryMode.NON_BUSINESS }
    }
}