package com.example.myapps.features.list.compose_views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapps.core.ui.ShimmerSampleScreen
import com.example.myapps.features.list.mvi.ListAction
import com.example.myapps.features.list.mvi.ListState
import com.example.myapps.features.list.mvi.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(
    state: ListState,
    onAction: (ListAction) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Установленные приложения") }) }
    ) { padding ->
        when (state.uiState) {
            is UiState.Success -> {
                LazyColumn(contentPadding = padding) {
                    items(state.uiState.appList) { app ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onAction(ListAction.OnAppClicked(app)) }
                                .padding(16.dp)
                        ) {
                            Text(app.name, fontWeight = FontWeight.Bold)
                            Text(app.packageName)
                        }
                        HorizontalDivider()
                    }
                }
            }

            is UiState.Error -> {
                Text("Ошибка")
            }

            is UiState.Empty -> {
                Text("Пустой список")
            }

            is UiState.Loading -> {
                ShimmerSampleScreen()
            }
        }
    }
}
