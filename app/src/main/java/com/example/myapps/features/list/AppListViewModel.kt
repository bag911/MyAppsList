package com.example.myapps.features.list

import androidx.lifecycle.viewModelScope
import com.example.myapps.core.MVIViewModel
import com.example.myapps.features.detail.mvi.DetailAction
import com.example.myapps.features.detail.mvi.DetailEvent
import com.example.myapps.features.detail.mvi.DetailState
import com.example.myapps.features.list.data.AppChecker
import com.example.myapps.features.list.mvi.ListAction
import com.example.myapps.features.list.mvi.ListEvent
import com.example.myapps.features.list.mvi.ListState
import com.example.myapps.features.list.mvi.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val appChecker: AppChecker,
) : MVIViewModel<ListState, ListAction, ListEvent>(initialState = ListState()) {

    init {
        loadApps()
    }

    private fun loadApps() {
        val handler = CoroutineExceptionHandler { _, exception ->
            updateState { copy(uiState = UiState.Error) }
        }
        viewModelScope.launch(Dispatchers.Default + handler) {
            val result = appChecker.getInstalledApps()

            if (result.isEmpty()) {
                updateState { copy(uiState = UiState.Empty) }
            } else {
                updateState { copy(uiState = UiState.Success(result)) }
            }
        }
    }

    override fun onAction(action: ListAction) {
        when (action) {
            is ListAction.OnAppClicked -> {
                emitEvent(ListEvent.OpenAppDetails(action.appInfo))
            }
        }
    }
}

