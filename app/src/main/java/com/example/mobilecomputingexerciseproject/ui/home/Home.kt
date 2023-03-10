package com.example.mobilecomputingexerciseproject.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.ui.home.categoryReminder.CategoryReminder

@Composable
fun Home(
    navController: NavController
) {
    var dropDownMenuExpanded by remember {
        mutableStateOf(false)
    }

    Scaffold(
        modifier = Modifier.padding(bottom = 24.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("createReminder") },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            TopAppBar(
                title = {
                    Text(text = "Reminders")
                },
                actions = {
                    TopAppBarActionButton(
                        imageVector = Icons.Outlined.MoreVert,
                        description = "Options"
                    ) {
                        // show the drop down menu
                        dropDownMenuExpanded = true
                    }
                    DropdownMenu(
                        expanded = dropDownMenuExpanded,
                        onDismissRequest = {
                            dropDownMenuExpanded = false
                        },
                        offset = DpOffset(x = 0.dp, y = (0).dp)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("profile")
                                dropDownMenuExpanded = false
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(Icons.Filled.Person),
                                    contentDescription = "ProfileIcon",
                                )
                                Text(
                                    " Profile",
                                    modifier = Modifier
                                        .width(100.dp)

                                )
                            }
                        }

                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("createReminder")
                                dropDownMenuExpanded = false
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(Icons.Filled.Add),
                                    contentDescription = "AddReminderIcon",
                                )
                                Text(
                                    " Add reminder",
                                    modifier = Modifier
                                        .width(130.dp),
                                    //fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        //---------------------------------------------------------------------

                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("nearbyReminders")
                                dropDownMenuExpanded = false
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(Icons.Filled.LocationOn),
                                    contentDescription = "nearbyReminders"
                                )
                                Text(
                                    " Reminder map",
                                    modifier = Modifier
                                        .width(150.dp),
                                )
                            }
                        }
                        //---------------------------------------------------------------
                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("login")
                                dropDownMenuExpanded = false
                            }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = rememberVectorPainter(Icons.Filled.ExitToApp),
                                    contentDescription = "LogoutIcon",
                                    tint = Color.Red
                                )
                                Text(
                                    " Log Out",
                                    modifier = Modifier
                                        .width(100.dp),
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            )

            CategoryReminder(navController)

        }
    }

}

@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }
}
