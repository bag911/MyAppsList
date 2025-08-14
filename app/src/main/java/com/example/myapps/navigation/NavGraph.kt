package com.example.myapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapps.presentation.detail.compose_views.AppDetailScreenWrapper
import com.example.myapps.presentation.list.compose_views.AppListScreenWrapper

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.List.route) {
        composable(
            route = Screen.List.route,
            content = {
                AppListScreenWrapper(navController)
            }
        )
        composable(
            route = Screen.Detail.route,
            content = {
                AppDetailScreenWrapper(navController = navController)
            },
        )
    }
}

sealed class Screen(val route: String) {
    data object List : Screen("list")
    data object Detail : Screen("detail_screen")
}