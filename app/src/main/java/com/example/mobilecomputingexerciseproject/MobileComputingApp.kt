package com.example.mobilecomputingexerciseproject

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobilecomputingexerciseproject.ui.home.Home
import com.example.mobilecomputingexerciseproject.ui.login.Login


@Composable
fun MobileComputingApp (
    appState: MobileComputingAppState = rememberMobileComputingAppState()
){
    NavHost(
        navController = appState.navController,
        startDestination = "login"
    ){
        composable(route = "login"){
            Login(navController = appState.navController)
        }
        composable(route = "home"){
            Home()
        }
    }
}