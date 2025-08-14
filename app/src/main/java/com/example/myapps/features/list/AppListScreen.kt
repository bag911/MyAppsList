package com.example.myapps.features.list

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapps.features.list.mvi.ListAction
import com.example.myapps.features.list.mvi.ListState
import com.example.myapps.features.list.mvi.UiState
import java.io.File
import java.security.MessageDigest

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
                        Divider()
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

@Composable
fun ShimmerSampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        ShimmerBox(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    baseColor: Color = Color.LightGray.copy(alpha = 0.6f),
    highlightColor: Color = Color.White.copy(alpha = 0.6f)
) {
    val transition = rememberInfiniteTransition()
    val xShimmer by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(xShimmer - 200f, 0f),
        end = Offset(xShimmer, 0f)
    )

    Box(
        modifier = modifier
            .background(brush)
    )
}


fun File.sha1(): String {
    val digest = MessageDigest.getInstance("SHA-1")
    inputStream().use { fis ->
        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (fis.read(buffer).also { bytesRead = it } != -1) {
            digest.update(buffer, 0, bytesRead)
        }
    }
    return digest.digest().joinToString("") { "%02x".format(it) }
}