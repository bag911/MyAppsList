package com.example.myapps.features.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapps.features.list.mvi.ListEvent
import com.example.myapps.navigation.Screen

@Composable
fun AppListScreenWrapper(
    navController: NavController,
    viewModel: AppListViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is ListEvent.OpenAppDetails -> {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "appInfo",
                        value = event.appInfo
                    )
                    navController.navigate(Screen.Detail.route)
                }
            }
        }
    }

    AppListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}