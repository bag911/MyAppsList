package com.example.myapps.presentation.list.mvi

import com.example.myapps.domain.model.AppInfo

sealed interface ListAction {
    data class OnAppClicked(val appInfo: AppInfo): ListAction
    data object OnRefresh: ListAction
}