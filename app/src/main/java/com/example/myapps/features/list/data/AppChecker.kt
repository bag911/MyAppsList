package com.example.myapps.features.list.data

import android.content.Context
import android.content.pm.PackageManager
import com.example.myapps.AppInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class AppChecker @Inject constructor(@ApplicationContext private val appContext: Context) {

    fun getInstalledApps(): List<AppInfo> {
        val pm = appContext.packageManager
        val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)
        return packages
            .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
            .map { pkg ->
                AppInfo(
                    name = pkg.applicationInfo?.loadLabel(pm).toString(),
                    packageName = pkg.packageName,
                    versionName = pkg.versionName ?: "",
                    apkPath = pkg.applicationInfo?.sourceDir
                )
            }
    }
}