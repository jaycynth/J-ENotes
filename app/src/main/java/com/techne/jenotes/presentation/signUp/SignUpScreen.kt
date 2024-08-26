package com.techne.jenotes.presentation.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.RegisterReceiverFlags
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.CustomOutlinedTextField
import com.techne.jenotes.presentation.common.HeaderText
import com.techne.jenotes.presentation.common.LoginWithGoogleText
import com.techne.jenotes.presentation.common.LogoText
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.greyText
import com.techne.jenotes.presentation.ui.theme.plusJakartaSans
import okhttp3.internal.wait

@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LogoText(fontSize = 30.sp)
                Spacer(modifier = Modifier.height(30.dp))
                HeaderText(
                    title = "Register",
                    titleColor = Color.White,
                    subtitle = "Create your account",
                    subtitleColor = Color.White
                )
                RegistrationForm(
                    fullName = fullname,
                    onFullNameChange = { fullname = it },
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = { confirmPassword = it },
                    onRegisterClick = {},
                )
                Spacer(modifier = Modifier.height(20.dp))

                LoginWithGoogleText(onClick = { /*TODO*/ }, color = Color.White)

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 30.dp)
                ) {
                    Text(
                        text = "Already have an account? ",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = plusJakartaSans,
                            fontWeight = FontWeight(400),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    Text(
                        text = "Login",
                        modifier = Modifier.clickable(onClick = { navController.navigate("login") }),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = plusJakartaSans,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun RegistrationForm(
    fullName: String,
    onFullNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        CustomOutlinedTextField(
            value = fullName,
            onValueChange = onFullNameChange,
            label = "Full Name",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Full Name Icon",
                    tint = Color.White
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email Address",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon",
                    tint = Color.White
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon",
                    tint = Color.White
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirm Password",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Confirm Password Icon",
                    tint = Color.White
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onRegisterClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = bgColor
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Create Account")
        }
    }
}
