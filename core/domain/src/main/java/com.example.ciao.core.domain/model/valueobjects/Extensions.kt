package com.example.ciao.core.domain.model.valueobjects

import android.util.Patterns

fun String.isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
