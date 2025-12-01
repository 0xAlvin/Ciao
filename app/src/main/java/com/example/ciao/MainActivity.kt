package com.example.ciao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ciao.core.ui.theme.CiaoTheme
import com.example.ciao.feature.auth.presentation.signin.SignInScreen
import com.example.ciao.feature.auth.presentation.signup.SignUpScreen
import com.example.ciao.feature.auth.presentation.forgotpassword.ForgotPasswordScreen
import com.example.ciao.feature.auth.presentation.onboarding.OnboardingScreen
import com.example.ciao.feature.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CiaoTheme {
                val viewModel: MainViewModel = hiltViewModel()
                val isLoading by viewModel.isLoading.collectAsState()
                val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()

                if (!isLoading) {
                    val navController = rememberNavController()
                    val startDestination = if (isUserLoggedIn) "home" else "onboarding"

                    NavHost(navController = navController, startDestination = startDestination) {
                        composable("onboarding") {
                            OnboardingScreen(
                                onNavigateToSignIn = { navController.navigate("signin") }
                            )
                        }
                        composable("signin") {
                            SignInScreen(
                                onNavigateToSignUp = { navController.navigate("signup") },
                                onNavigateToForgotPassword = { navController.navigate("forgot_password") },
                                onSignInSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("signup") {
                            SignUpScreen(
                                onNavigateBack = { navController.popBackStack() },
                                onSignUpSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("onboarding") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("forgot_password") {
                            ForgotPasswordScreen(
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable("home") {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}
