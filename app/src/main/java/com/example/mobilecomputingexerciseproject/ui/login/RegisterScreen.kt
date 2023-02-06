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
fun RegisterUser (
    navController: NavController
){
    Surface(modifier = Modifier.fillMaxSize()) {
        val username = remember { mutableStateOf("") }
        val firstName = remember { mutableStateOf("") }
        val lastName = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password  = remember { mutableStateOf("") }
        val confirmPassword  = remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,

        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Create Account",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstName.value,
                onValueChange = {text -> firstName.value = text},
                label = { Text(text = "First Name")},
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName.value,
                onValueChange = {text -> lastName.value = text},
                label = { Text(text = "Last Name")},
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username.value,
                onValueChange = {text -> username.value = text},
                label = { Text(text = "Username")},
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email.value,
                onValueChange = {text -> email.value = text},
                label = { Text(text = "Email Address")},
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                onValueChange = {passwordText -> password.value = passwordText},
                label = {Text (text = "Password")},
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPassword.value,
                onValueChange = {passwordText -> confirmPassword.value = passwordText},
                label = {Text (text = "Confirm Password")},
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
                Text(text = "Confirm Registration")
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
                onClick = { navController.navigate("login") },
                border = BorderStroke(1.dp, Color.Green),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}