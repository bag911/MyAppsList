package com.example.myapps.data

import android.content.Context
import android.content.pm.PackageManager
import com.example.myapps.domain.model.AppInfo
import com.example.myapps.domain.AppListRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppListListRepositoryImpl @Inject constructor(@ApplicationContext private val appContext: Context):
    AppListRepository {
    override fun getInstalledApps(): List<AppInfo> {
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