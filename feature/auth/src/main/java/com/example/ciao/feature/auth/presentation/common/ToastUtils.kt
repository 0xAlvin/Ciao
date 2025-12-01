package com.example.ciao.feature.auth.presentation.common

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(message: String?, onDismiss: () -> Unit = {}) {
    val context = LocalContext.current
    
    LaunchedEffect(message) {
        message?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            onDismiss()
        }
    }
}
