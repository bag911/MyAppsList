package com.example.myapps.presentation.detail.mvi

import com.example.myapps.domain.model.AppInfo

sealed interface DetailAction {
    data class LoadAppDetails(val appInfo: AppInfo?) : DetailAction
    data object OnLaunchClicked : DetailAction
    data object OnBack : DetailAction
}