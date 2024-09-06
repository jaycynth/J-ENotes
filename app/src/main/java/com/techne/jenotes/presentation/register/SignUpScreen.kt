package com.techne.jenotes.presentation.register

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.techne.jenotes.presentation.common.CustomOutlinedTextField
import com.techne.jenotes.presentation.common.HeaderText
import com.techne.jenotes.presentation.common.LoginWithGoogleText
import com.techne.jenotes.presentation.common.LogoText
import com.techne.jenotes.presentation.ui.theme.bgColor
import com.techne.jenotes.presentation.ui.theme.plusJakartaSans
import com.techne.jenotes.presentation.ui.theme.whiteColor
import com.techne.jenotes.presentation.util.showToast

@Composable
fun SignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    onBackPress: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val registerState by viewModel.registerState.collectAsState()

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showLoader by remember { mutableStateOf(false) }

    val fullNameFocusRequester = remember { FocusRequester() }
    val usernameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }

    val context = LocalContext.current

    LaunchedEffect(registerState) {
        when (registerState) {
            is RegisterState.Success -> {
                context.showToast((registerState as RegisterState.Success).signUpResponse.message)
                navController.navigate("login")
            }
            is RegisterState.Loading -> showLoader = true
            is RegisterState.Error -> {
                showLoader = false
                context.showToast((registerState as RegisterState.Error).message)
            }
            RegisterState.Idle -> showLoader = false
        }
    }


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
                BackHandler {
                    onBackPress()
                }
                LogoText(fontSize = 30.sp)
                Spacer(modifier = Modifier.height(30.dp))
                HeaderText(
                    title = "Register",
                    titleColor = Color.White,
                    subtitle = "Create your account",
                    subtitleColor = Color.White
                )
                RegistrationForm(
                    fullName = fullName,
                    onFullNameChange = { fullName = it },
                    userName = username,
                    onUserNameChange = { username = it },
                    email = email,
                    onEmailChange = { email = it },
                    password = password,
                    onPasswordChange = { password = it },
                    confirmPassword = confirmPassword,
                    onConfirmPasswordChange = { confirmPassword = it },
                    showLoader = showLoader,
                    fullNameFocusRequester = fullNameFocusRequester,
                    usernameFocusRequester = usernameFocusRequester,
                    emailFocusRequester = emailFocusRequester,
                    passwordFocusRequester = passwordFocusRequester,
                    confirmPasswordFocusRequester = confirmPasswordFocusRequester,
                    onRegisterClick = {
                        viewModel.handleIntent(
                            RegisterEvent.Submit(
                                fullName = fullName,
                                confirmPassword = confirmPassword,
                                userName = username,
                                email = email,
                                password = password
                            )
                        )
                    },
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
    userName: String,
    onUserNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLoader: Boolean,
    fullNameFocusRequester: FocusRequester,
    usernameFocusRequester: FocusRequester,
    emailFocusRequester: FocusRequester,
    passwordFocusRequester: FocusRequester,
    confirmPasswordFocusRequester: FocusRequester,
) {
    Column(
        modifier = modifier
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally

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
            },
            modifier = Modifier.focusRequester(fullNameFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onNext = { usernameFocusRequester.requestFocus() }
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomOutlinedTextField(
            value = userName,
            onValueChange = onUserNameChange,
            label = "Username",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Full Name Icon",
                    tint = Color.White
                )
            },
            modifier = Modifier.focusRequester(usernameFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onNext = { emailFocusRequester.requestFocus() }
            )
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
            },
            modifier = Modifier.focusRequester(emailFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
            ),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            )
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.focusRequester(passwordFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
            ),
            keyboardActions = KeyboardActions(
                onNext = { confirmPasswordFocusRequester.requestFocus() }
            )
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.focusRequester(confirmPasswordFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onRegisterClick() }
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        if (showLoader) CircularProgressIndicator(
            color = whiteColor, strokeWidth = 2.dp
        ) else Button(
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
