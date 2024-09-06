package com.techne.jenotes

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techne.jenotes.presentation.home.HomeScreen
import com.techne.jenotes.presentation.login.LoginScreen
import com.techne.jenotes.presentation.login.LoginViewModel
import com.techne.jenotes.presentation.notes.EditorScreen
import com.techne.jenotes.presentation.register.RegisterViewModel
import com.techne.jenotes.presentation.register.SignUpScreen
import com.techne.jenotes.presentation.splash.SplashScreen
import com.techne.jenotes.presentation.ui.theme.JENotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JENotesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val context = LocalContext.current

                    NavHost(navController = navController, startDestination = "splash") {
                        composable("splash") {
                            SplashScreen(navController = navController)
                        }
                        composable("login") {
                            LoginScreen(
                                navController = navController,
                                viewModel = loginViewModel,
                                onBackPress = {
                                    (context as? Activity)?.finish()
                                })
                        }
                        composable("signup") {
                            SignUpScreen(
                                navController = navController,
                                viewModel = registerViewModel,
                                onBackPress = {
                                    (context as? Activity)?.finish()
                                }
                            )
                        }
                        composable("home") {
                            HomeScreen(navController = navController, onBackPress = {
                                (context as? Activity)?.finish()
                            })
                        }
                        composable("editor") {
                            EditorScreen()
                        }

                    }
                }
            }
        }
    }
}
