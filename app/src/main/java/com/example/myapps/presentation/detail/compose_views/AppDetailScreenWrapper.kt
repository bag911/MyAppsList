package com.example.myapps.presentation.detail.compose_views

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapps.domain.model.AppInfo
import com.example.myapps.R
import com.example.myapps.presentation.detail.AppDetailViewModel
import com.example.myapps.presentation.detail.mvi.DetailAction
import com.example.myapps.presentation.detail.mvi.DetailEvent

@Composable
fun AppDetailScreenWrapper(
    navController: NavController,
    viewModel: AppDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val appInfo = navController.previousBackStackEntry
        ?.savedStateHandle
        ?.get<AppInfo>("appInfo")

    LaunchedEffect(Unit) {
        viewModel.onAction(DetailAction.LoadAppDetails(appInfo))
    }

    LaunchedEffect(key1 = viewModel) {
        viewModel.events.collect { event ->
            when (event) {
                is DetailEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is DetailEvent.LaunchApp -> {
                    launchApp(context, event.packageName)
                }

                is DetailEvent.Back -> {
                    navController.popBackStack()
                }
            }
        }
    }

    AppDetailScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

private fun launchApp(context: Context, packageName: String) {
    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        context.startActivity(launchIntent)
    } else {
        Toast.makeText(
            context,
            context.getString(R.string.app_not_installed_message),
            Toast.LENGTH_SHORT
        ).show()
    }
}