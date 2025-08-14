package com.example.myapps.presentation.list

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.myapps.R
import com.example.myapps.core.MVIViewModel
import com.example.myapps.domain.AppListRepository
import com.example.myapps.presentation.list.mvi.ListAction
import com.example.myapps.presentation.list.mvi.ListEvent
import com.example.myapps.presentation.list.mvi.ListState
import com.example.myapps.presentation.list.mvi.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val repository: AppListRepository,
    @ApplicationContext private val appContext: Context,
) : MVIViewModel<ListState, ListAction, ListEvent>(initialState = ListState()) {

    init { loadApps() }

    override fun onAction(action: ListAction) {
        when (action) {
            is ListAction.OnAppClicked -> emitEvent(ListEvent.OpenAppDetails(action.appInfo))
            is ListAction.OnRefresh -> loadApps()
        }
    }

    private fun loadApps() {
        val handler = CoroutineExceptionHandler { _, exception ->
            updateState {
                copy(
                    uiState = UiState.Error(
                        exception.message ?: appContext.getString(R.string.unknown_error)
                    )
                )
            }
        }

        viewModelScope.launch(Dispatchers.Default + handler) {
            val result = repository.getInstalledApps()
            if (result.isEmpty()) {
                updateState { copy(uiState = UiState.Empty) }
            } else {
                updateState { copy(uiState = UiState.Success(result)) }
            }
        }
    }
}

