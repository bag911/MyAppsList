package com.example.myapps.features.list.mvi

import androidx.compose.runtime.Immutable
import com.example.myapps.AppInfo

@Immutable
data class ListState(
    val uiState: UiState = UiState.Loading,
)

sealed interface UiState {
    data class Success(val appList: List<AppInfo>) : UiState
    data object Error : UiState
    data object Loading : UiState
    data object Empty : UiState
}