package com.example.mobilecomputingexerciseproject.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Profile(
    onBackPress: () -> Unit
){
    Surface{
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.Green,
                        contentDescription = null
                    )
                }
            }
        }
    }
}