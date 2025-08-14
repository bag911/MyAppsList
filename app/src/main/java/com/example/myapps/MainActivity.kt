package com.example.myapps

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.myapps.navigation.NavGraph
import com.example.myapps.ui.theme.MyAppsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppsTheme {
                NavGraph()
            }
        }
    }


}


@Parcelize
data class AppInfo(
    val name: String,
    val packageName: String,
    val versionName: String,
    val apkPath: String?
): Parcelable