package com.example.mobilecomputingexerciseproject.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Login (
    navController: NavController
){
    Surface(modifier = Modifier.fillMaxSize()) {
        val username = remember { mutableStateOf("") }
        val password  = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center

        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                    )
                Text(
                    text = "to",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                )
                Text(
                    text = "ReminderApp",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username.value,
                onValueChange = {text -> username.value = text},
                label = { Text(text = "Username")},
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                onValueChange = {passwordText -> password.value = passwordText},
                label = {Text (text = "Password")},
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate("home") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp)
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "OR",
                    fontWeight = FontWeight.Bold,

                    )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { navController.navigate("registerUser") },
                border = BorderStroke(1.dp, Color.Green),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp)
            ) {
                Text(text = "Sign up")
            }

        }
    }

}