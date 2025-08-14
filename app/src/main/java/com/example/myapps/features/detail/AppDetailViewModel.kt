package com.example.myapps.features.detail

import com.example.myapps.core.MVIViewModel
import com.example.myapps.features.detail.data.FileEncoder
import com.example.myapps.features.detail.mvi.DetailAction
import com.example.myapps.features.detail.mvi.DetailEvent
import com.example.myapps.features.detail.mvi.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val fileEncoder: FileEncoder,
) : MVIViewModel<DetailState, DetailAction, DetailEvent>(initialState = DetailState()) {

    override fun onAction(action: DetailAction) {
        when (action) {
            is DetailAction.LoadAppDetails -> {
                if (action.appInfo == null) { emitEvent(DetailEvent.ShowToast("Smth went wrong"))}
                updateState {
                    copy(
                        appName = action.appInfo?.name.orEmpty(),
                        packageName = action.appInfo?.packageName.orEmpty(),
                        versionName = action.appInfo?.versionName.orEmpty(),
                        checkSum = fileEncoder.getApkChecksum(action.appInfo?.apkPath.orEmpty())
                    )
                }
            }

            is DetailAction.OnLaunchClicked -> {
                emitEvent(DetailEvent.LaunchApp(currentState.packageName))
            }
        }
    }
}

