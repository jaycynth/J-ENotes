package com.techne.jenotes

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techne.jenotes.presentation.home.HomeScreen
import com.techne.jenotes.presentation.login.LoginScreen
import com.techne.jenotes.presentation.signUp.SignUpScreen
import com.techne.jenotes.presentation.splash.SplashScreen
import com.techne.jenotes.presentation.ui.theme.JENotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                            LoginScreen(navController = navController){
                                (context as? Activity)?.finish()
                            }
                        }
                        composable("signup") {
                            SignUpScreen(navController = navController)
                        }
                        composable("home") {
                            HomeScreen(navController = navController)
                        }

                    }
                }
            }
        }
    }
}
