package com.example.studentcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studentcard.ui.LoginScreen
import com.example.studentcard.ui.StudentCardScreen
import com.example.studentcard.ui.AdminSetupScreen

enum class Screen {
    Login,
    Card,
    Admin
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        var currentScreen by remember { mutableStateOf(Screen.Login) }

        Box(modifier = Modifier.fillMaxSize()) {
            when (currentScreen) {
                Screen.Login -> LoginScreen(
                    onLoginSuccess = { currentScreen = Screen.Card }
                )
                Screen.Card -> StudentCardScreen(
                    onAdminClick = { currentScreen = Screen.Admin }
                )
                Screen.Admin -> AdminSetupScreen(
                    onSave = { currentScreen = Screen.Card }
                )
            }
        }
    }
}