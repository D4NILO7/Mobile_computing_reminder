package com.example.mobilecomputingexerciseproject.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun Profile(
    navController: NavController
) {
    val db = Firebase.firestore
    var fAuth = FirebaseAuth.getInstance()

    var userProfile: ProfileClass? = null
    var username = remember { mutableStateOf("") }
    var firstName = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    val docRef = db.collection("users").document(fAuth.uid.toString())

    docRef.get()
        .addOnSuccessListener { snapShots ->
            userProfile = snapShots.toObject(ProfileClass::class.java)
            username.value = userProfile?.userName.toString()
            firstName.value = userProfile?.firstName.toString()
            lastName.value = userProfile?.lastName.toString()
            email.value = userProfile?.email.toString()
        }


    Surface(modifier = Modifier.fillMaxHeight()) {
        Column(
        ) {
            CreateTopBar(navController = navController)
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 96.dp)
                    .fillMaxSize()
                    .background(Color(0xFF3E4445)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                )

                Spacer(modifier = Modifier.height(18.dp))
                Row(
                ) {
                    Text(firstName.value, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text(lastName.value, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                }
                Text("#${username.value}", fontSize = 24.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Text(email.value, fontSize = 24.sp)

            }

        }
    }
}

@Composable
fun CreateTopBar(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        TopAppBar(
            title = {
                Text(text = "Profile")
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
        )
    }
}