package com.techne.jenotes.presentation.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.LoginWithGoogleText
import com.techne.jenotes.presentation.common.LogoText
import com.techne.jenotes.presentation.ui.theme.bgColor


@Composable
fun SplashScreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold { paddingValues ->
        Column(
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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            LogoText()

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(50.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
            ) {
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    onClick = { navController.navigate("signup") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, contentColor = bgColor
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Text(text = "Sign up")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4285F4), contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.White),
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(50.dp))

                LoginWithGoogleText(
                    modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }, color = Color.White
                )
            }
        }
    }
}
