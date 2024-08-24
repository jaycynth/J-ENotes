package com.techne.jenotes.presentation.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.HeaderText
import com.techne.jenotes.presentation.common.LogoText
import com.techne.jenotes.presentation.login.FieldsContainer
import okhttp3.internal.wait

@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Box(
            modifier
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF2492F6), Color(0xFF4B68EA)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoText()
                Spacer(modifier = Modifier.height(40.dp))
                HeaderText(title = "Register", titleColor = Color.White, subtitle = "Create your account", subtitleColor = Color.White)

            }
        }
    }
}