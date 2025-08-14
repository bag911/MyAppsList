package com.example.myapps.features.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myapps.AppInfo
import com.example.myapps.features.detail.mvi.DetailAction
import com.example.myapps.features.detail.mvi.DetailEvent
import com.example.myapps.features.detail.mvi.DetailState
import com.example.myapps.features.list.mvi.ListEvent
import com.example.myapps.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { Text("Детали приложения") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAction(DetailAction.OnLaunchClicked) }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "Launch")
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Название: ${state.appName}", fontWeight = FontWeight.Bold)
            Text("Версия: ${state.versionName}")
            Text("Пакет: ${state.packageName}")
            Text("SHA-1: ${state.checkSum}", modifier = Modifier.padding(top = 8.dp))
        }
    }
}


