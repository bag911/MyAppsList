package com.example.myapps.presentation.detail.compose_views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapps.presentation.detail.mvi.DetailState

@Preview
@Composable
private fun Content() {
    AppDetailScreen(
        state = DetailState(
            appName = "App Name",
            packageName = "com.example.app",
            versionName = "1.0",
            checkSum = "123456789"
        ),
        onAction = {}
    )
}
