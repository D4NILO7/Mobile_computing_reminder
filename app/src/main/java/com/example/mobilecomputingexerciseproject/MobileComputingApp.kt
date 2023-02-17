package com.example.mobilecomputingexerciseproject

import android.provider.ContactsContract
import android.provider.ContactsContract.Profile
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobilecomputingexerciseproject.ui.home.Home
import com.example.mobilecomputingexerciseproject.ui.login.Login
import com.example.mobilecomputingexerciseproject.ui.login.PinLogin
import com.example.mobilecomputingexerciseproject.ui.login.RegisterUser
import com.example.mobilecomputingexerciseproject.ui.profile.Profile
import com.example.mobilecomputingexerciseproject.ui.reminderUI.CreateReminder


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
            Home(navController = appState.navController)
        }
        composable(route = "profile"){
            Profile (navController = appState.navController)
        }
        composable(route = "registerUser"){
            RegisterUser(navController = appState.navController)
        }
        composable(route = "pinLogin"){
            PinLogin(navController = appState.navController)
        }
        composable(route = "createReminder"){
            CreateReminder(navController = appState.navController)
        }
    }
}
