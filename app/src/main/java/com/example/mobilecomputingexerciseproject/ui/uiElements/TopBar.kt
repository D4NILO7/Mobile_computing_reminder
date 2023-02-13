package com.example.mobilecomputingexerciseproject.ui.uiElements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CreateTopBar(
    navController: NavController,
    header: String,
    logOutIcon: Boolean,
    submitButton: Boolean,
    submitAction: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Text(text = header)
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigate("home") }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color(0xFF00C6CF),
                        contentDescription = null
                    )
                }
            },
            actions = {

                //logout icon for profile reminder
                if (logOutIcon) {
                    IconButton(
                        onClick = { navController.navigate("login") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            tint = Color.Red,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                }

                //submit icon for create,edit reminder
                if (submitButton) {
                    IconButton(
                        onClick = { submitAction()}
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            tint = Color(0xFF00C6CF),
                            contentDescription = null,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
        )
    }
}