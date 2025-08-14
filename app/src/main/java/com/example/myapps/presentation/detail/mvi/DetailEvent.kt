package com.example.myapps.presentation.detail.mvi

sealed interface DetailEvent {
    data class ShowToast(val message: String) : DetailEvent
    data class LaunchApp(val packageName: String) : DetailEvent
    data object Back : DetailEvent
}