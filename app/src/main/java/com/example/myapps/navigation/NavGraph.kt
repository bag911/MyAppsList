package com.example.myapps.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapps.AppInfo
import com.example.myapps.features.detail.AppDetailScreenWrapper
import com.example.myapps.features.list.AppListScreenWrapper

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            AppListScreenWrapper(navController)
        }
        composable(
            route = Screen.Detail.route,
        ) { backStackEntry ->
            AppDetailScreenWrapper( navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    data object List : Screen("list")
    data object Detail : Screen("detail_screen")
}