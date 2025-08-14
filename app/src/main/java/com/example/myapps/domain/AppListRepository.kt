package com.example.myapps.domain

import com.example.myapps.domain.model.AppInfo


interface AppListRepository {
    fun getInstalledApps(): List<AppInfo>
}