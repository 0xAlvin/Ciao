package com.example.ciao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ciao.core.ui.theme.CiaoTheme
import com.example.ciao.feature.auth.login.presentation.LoginScreen
import com.example.ciao.feature.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CiaoTheme {
                LoginScreen(
                    onLoginSuccess = {}
                )
            }
        }
    }
}
