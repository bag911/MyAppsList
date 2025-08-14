package com.example.myapps.presentation.detail

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.myapps.R
import com.example.myapps.core.MVIViewModel
import com.example.myapps.core.utils.FileEncoder
import com.example.myapps.presentation.detail.mvi.DetailAction
import com.example.myapps.presentation.detail.mvi.DetailEvent
import com.example.myapps.presentation.detail.mvi.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val fileEncoder: FileEncoder,
    @ApplicationContext private val appContext: Context,
    ) : MVIViewModel<DetailState, DetailAction, DetailEvent>(initialState = DetailState()) {

    override fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.LoadAppDetails -> handleLoadAppDetails(action)

            is DetailAction.OnLaunchClicked -> handleLaunchClicked()

            is DetailAction.OnBack -> handleBack()
        }
    }

    private fun handleBack() {
        emitEvent(DetailEvent.Back)
    }

    private fun handleLaunchClicked() {
        emitEvent(DetailEvent.LaunchApp(currentState.packageName))
    }

    private fun handleLoadAppDetails(action: DetailAction.LoadAppDetails) {
        if (action.appInfo == null) {
            emitEvent(DetailEvent.ShowToast(appContext.getString(R.string.smth_went_wrong)))
        }
        updateState {
            copy(
                appName = action.appInfo?.name.orEmpty(),
                packageName = action.appInfo?.packageName.orEmpty(),
                versionName = action.appInfo?.versionName.orEmpty(),
            )
        }

        action.appInfo?.let {
            viewModelScope.launch(Dispatchers.Default) {
                updateState {
                    copy(
                        checkSum = fileEncoder.getApkChecksum(
                            it.apkPath.orEmpty()
                        )
                    )
                }
            }
        } ?: updateState { copy(checkSum = appContext.getString(R.string.error)) }
    }
}

