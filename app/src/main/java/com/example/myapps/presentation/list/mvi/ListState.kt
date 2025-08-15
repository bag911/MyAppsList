package com.example.myapps.presentation.list.mvi

import androidx.compose.runtime.Immutable
import com.example.myapps.domain.model.AppInfo

@Immutable
data class ListState(
    val uiState: UiState = UiState.Loading,
)

sealed interface UiState {
    data class Success(val appList: List<AppInfo>) : UiState
    data class Error(val message: String) : UiState
    data object Loading : UiState
    data object Empty : UiState
}