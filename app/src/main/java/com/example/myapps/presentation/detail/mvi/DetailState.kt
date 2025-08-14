package com.example.myapps.presentation.detail.mvi

import androidx.compose.runtime.Immutable

@Immutable
data class DetailState(
    val appName: String = "",
    val versionName: String = "",
    val packageName: String = "",
    val apkPath: String = "",
    val checkSum: String = ""
)