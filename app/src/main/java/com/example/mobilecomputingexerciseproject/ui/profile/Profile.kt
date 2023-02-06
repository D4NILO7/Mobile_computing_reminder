package com.example.mobilecomputingexerciseproject.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController


@Composable
fun Profile(
    navController: NavController
){
    Surface{
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CreateTopBar(navController = navController )
        }
    }
}

@Composable
fun CreateTopBar(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        TopAppBar(
            title = {
                Text(text = "Profile")
            },
            navigationIcon = {
                IconButton(
                    onClick = {navController.navigate("home")}
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.Green,
                        contentDescription = null
                    )
                } },
            actions = {
                Text(text = "Log Out", fontWeight = FontWeight.Bold, color = Color.Red)
                IconButton(
                    onClick = {navController.navigate("login")}
                ){
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        tint = Color.Red,
                        contentDescription = null
                    )
                }

            }
        )
    }
}