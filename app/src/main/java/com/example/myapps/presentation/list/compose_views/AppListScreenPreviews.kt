package com.example.myapps.presentation.list.compose_views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapps.domain.model.AppInfo
import com.example.myapps.presentation.list.mvi.ListState
import com.example.myapps.presentation.list.mvi.UiState

@Preview
@Composable
private fun Empty() {
    AppListScreen(
        state = ListState(uiState = UiState.Empty),
        onAction = {}
    )
}

@Preview
@Composable
private fun Error() {
    AppListScreen(
        state = ListState(uiState = UiState.Error(message = "Error message")),
        onAction = {}
    )
}

@Preview
@Composable
private fun Loading() {
    AppListScreen(
        state = ListState(uiState = UiState.Loading),
        onAction = {}
    )
}

@Preview
@Composable
private fun Content() {
    AppListScreen(
        state = ListState(
            uiState = UiState.Success(
                listOf(
                    AppInfo(
                        name = "App 1",
                        packageName = "com.example.app1",
                        versionName = "1.0",
                        apkPath = "2123"
                    ),
                    AppInfo(
                        name = "App 2",
                        packageName = "com.example.app2",
                        versionName = "2.0",
                        apkPath = "3123"
                    ),
                    AppInfo(
                        name = "App 3",
                        packageName = "com.example.app3",
                        versionName = "3.0",
                        apkPath = "4123"
                    ),
                )
            )
        ),
        onAction = {}
    )
}