package com.example.dne

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.example.dne.data.StudentRepository
import com.example.dne.ui.LoginScreen
import com.example.dne.ui.dneScreen
import com.example.dne.ui.AdminSetupScreen
import org.jetbrains.compose.resources.painterResource
import dne.composeapp.generated.resources.Res
import dne.composeapp.generated.resources.background

enum class Screen {
    Login,
    Card,
    Admin
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        val repository = remember { StudentRepository() }
        var currentScreen by remember {
            mutableStateOf(
                if (repository.hasStudentData()) Screen.Card else Screen.Login
            )
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFE3E7F3)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(Res.drawable.background),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                when (currentScreen) {
                    Screen.Login -> LoginScreen(
                        onLoginSuccess = { currentScreen = Screen.Card }
                    )
                    Screen.Card -> dneScreen(
                        onAdminClick = { currentScreen = Screen.Admin }
                    )
                    Screen.Admin -> AdminSetupScreen(
                        onSave = { currentScreen = Screen.Card }
                    )
                }
            }
        }
    }
}