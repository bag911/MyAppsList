package com.example.myapps.features.list.mvi

import com.example.myapps.AppInfo

sealed interface ListEvent {
    class OpenAppDetails(val appInfo: AppInfo) : ListEvent
}