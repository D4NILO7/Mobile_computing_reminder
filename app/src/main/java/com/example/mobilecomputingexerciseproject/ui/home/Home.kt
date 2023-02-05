package com.example.mobilecomputingexerciseproject.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Home(

){
    Surface{
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            TopAppBar {
                IconButton(
                    onClick = {}
                ){
                    Icon(
                        imageVector = Icons.Filled.Face,
                        contentDescription = null
                    )
                }
            }
        }
    }
}