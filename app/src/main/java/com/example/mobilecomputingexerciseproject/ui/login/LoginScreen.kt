package com.example.mobilecomputingexerciseproject.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mobilecomputingexerciseproject.R

@Composable
fun LoginScreen (
    modifier: Modifier
){
    val username = remember { mutableStateOf("") }
    val password  = remember { mutableStateOf("") }
    Column(
        modifier = modifier.padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center

    ) {

        Icon(
            painter = rememberVectorPainter(Icons.Filled.Person),
            contentDescription = "LoginScreenIcon",
            modifier = Modifier.fillMaxWidth().size(150.dp)
        )
        
        Spacer(modifier = Modifier.height(10.dp))
        
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
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape( corner = CornerSize(20.dp)),
            contentPadding = PaddingValues(20.dp)
        ) {
            Text(text = "Login")
        }
    }
}