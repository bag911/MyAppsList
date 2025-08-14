package com.example.myapps.presentation.list.mvi

import com.example.myapps.domain.model.AppInfo

sealed interface ListEvent {
    class OpenAppDetails(val appInfo: AppInfo) : ListEvent
}