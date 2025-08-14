package com.example.myapps.presentation.list.compose_views

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapps.R
import com.example.myapps.core.ui.ShimmerSampleScreen
import com.example.myapps.core.ui.StubBox
import com.example.myapps.presentation.list.mvi.ListAction
import com.example.myapps.presentation.list.mvi.ListState
import com.example.myapps.presentation.list.mvi.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(
    state: ListState,
    onAction: (ListAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.listScreen_title)) }
            )
        }
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
                StubBox(
                    modifier = Modifier.padding(padding),
                    text = stringResource(R.string.listScreen_errorMessage, state.uiState.message),
                    onAction = { onAction(ListAction.OnRefresh) }
                )

            }

            is UiState.Empty -> {
                StubBox(
                    modifier = Modifier.padding(padding),
                    text = stringResource(R.string.listScreen_emptyListTitle),
                    onAction = { onAction(ListAction.OnRefresh) }
                )
            }

            is UiState.Loading -> {
                ShimmerSampleScreen()
            }
        }
    }
}
