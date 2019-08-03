package com.example.sarwan.tawseel.utils

import com.example.sarwan.tawseel.entities.HistoryMode
import com.example.sarwan.tawseel.entities.Profile
import com.example.sarwan.tawseel.repository.BaseRepository

fun Profile.mapProfileToHistory(): HistoryMode {
    return when(this) {
        Profile.BUSINESS-> { HistoryMode.BUSINESS }
        else -> { HistoryMode.NON_BUSINESS }
    }
}