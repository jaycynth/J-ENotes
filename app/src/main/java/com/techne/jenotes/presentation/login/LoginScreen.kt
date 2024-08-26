package com.techne.jenotes.presentation.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.HeaderText
import com.techne.jenotes.presentation.common.InputField
import com.techne.jenotes.presentation.common.LoginWithGoogleText
import com.techne.jenotes.presentation.common.LogoText
import com.techne.jenotes.presentation.common.PasswordInputField
import com.techne.jenotes.presentation.common.RoundedButton
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.greyText
import com.techne.jenotes.presentation.ui.theme.plusJakartaSans

@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier,onBackPress: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF2492F6), Color(0xFF4B68EA)),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackHandler {
                onBackPress()
            }
            LogoText()
            Spacer(modifier = Modifier.height(60.dp))
            FieldsContainer(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onLoginClick = { navController.navigate("home") },
                onForgotPasswordClick = { /* Handle forgot password */ },
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityToggle = { isPasswordVisible = !isPasswordVisible },
                navController
            )
        }

    }
}

@Composable
fun FieldsContainer(
    modifier: Modifier,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit,
    navController: NavController,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 52.dp, topEnd = 52.dp))
            .fillMaxHeight()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        HeaderText(title = "Welcome Back", titleColor = bgColor, subtitle = "Log in to your account", subtitleColor = greyText)
        Spacer(modifier = Modifier.height(50.dp))
        InputField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email Address",
            leadingIcon = Icons.Default.Email,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(30.dp))
        PasswordInputField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password",
            isPasswordVisible = isPasswordVisible,
            leadingIcon = Icons.Default.Lock,
            onPasswordVisibilityToggle = onPasswordVisibilityToggle
        )
        Spacer(modifier = Modifier.height(30.dp))
        RoundedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Login",
            onClick = onLoginClick
        )
        Spacer(modifier = Modifier.height(10.dp))
        ForgotPasswordText(onForgotPasswordClick)
        Spacer(modifier = Modifier.height(50.dp))
        LoginWithGoogleText(modifier, onClick = {}, color = greyText)
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 30.dp)) {
            Text(
                text = "Don’t have an account? ",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = plusJakartaSans,
                    fontWeight = FontWeight(400),
                    color = greyText,
                    textAlign = TextAlign.Center,
                ),
            )
            Text(
                text = "Sign Up",
                modifier = Modifier.clickable(onClick = { navController.navigate("signup") }),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = plusJakartaSans,
                    fontWeight = FontWeight.Bold,
                    color = bgColor,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}


@Composable
fun ForgotPasswordText(onForgotPasswordClick: () -> Unit) {
    Text(
        text = "Forgot Password?",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onForgotPasswordClick),
        style = TextStyle(fontSize = 12.sp),
        color = greyText,
        textAlign = TextAlign.End
    )
}
