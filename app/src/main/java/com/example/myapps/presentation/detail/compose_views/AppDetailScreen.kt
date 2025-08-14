package com.example.myapps.presentation.detail.compose_views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapps.R
import com.example.myapps.presentation.detail.mvi.DetailAction
import com.example.myapps.presentation.detail.mvi.DetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(
    state: DetailState,
    onAction: (DetailAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(DetailAction.OnBack) },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                },
                title = { Text(text = stringResource(R.string.detailScreen_title)) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(DetailAction.OnLaunchClicked) },
                content = {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Launch"
                    )
                }
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.d_nameText, state.appName),
                fontWeight = FontWeight.Bold
            )
            Text(text = stringResource(R.string.d_vesionText, state.versionName))
            Text(text = stringResource(R.string.d_packageText, state.packageName))

            val checkSumTxt =
                state.checkSum.takeIf { it.isNotBlank() } ?: stringResource(R.string.loading)
            Text(
                text = stringResource(R.string.d_checksumText, checkSumTxt),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


