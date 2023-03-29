package com.example.clonefacebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clonefacebook.signin.SignInScreen
import com.example.clonefacebook.ui.theme.CloneFacebookTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloneFacebookTheme {

                TransparentSystemBars()

                val navController = rememberNavController()
                val homeRoute = "home"
                val signInRoute = "signin"
                NavHost(navController, startDestination = homeRoute) {
                    composable(homeRoute) {
                        HomeScreen(
                            navigateToSignIn = {
                                navController.navigate(signInRoute) {
                                    popUpTo(homeRoute) {
                                        inclusive = true
                                    }
                                }
                            }
                        )

                    }
                      composable(signInRoute){
                       SignInScreen(navigateToHome = {
                           navController.navigate(homeRoute) {
                               popUpTo(signInRoute) {
                                   inclusive = true
                               }
                           }
                       }
                       )
                    }
                }

            }
        }
    }


    @Composable
    fun TransparentSystemBars() {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = MaterialTheme.colors.isLight

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }
    }

}
