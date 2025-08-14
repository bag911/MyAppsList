package com.example.myapps.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppInfo(
    val name: String,
    val packageName: String,
    val versionName: String,
    val apkPath: String?
): Parcelable