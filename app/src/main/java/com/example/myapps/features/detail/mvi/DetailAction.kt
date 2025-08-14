package com.example.myapps.features.detail.mvi

import com.example.myapps.AppInfo

sealed interface DetailAction {
    data class LoadAppDetails(val appInfo: AppInfo?) : DetailAction
    data object OnLaunchClicked : DetailAction
}