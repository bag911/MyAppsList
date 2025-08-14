package com.example.myapps.features.list.mvi

import com.example.myapps.AppInfo

sealed interface ListAction {
    data class OnAppClicked(val appInfo: AppInfo): ListAction
}