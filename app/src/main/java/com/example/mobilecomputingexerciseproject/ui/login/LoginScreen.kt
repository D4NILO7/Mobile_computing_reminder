package com.example.mobilecomputingexerciseproject.ui.login

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
        var otpValue by remember {
            mutableStateOf("")
        }
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
                label = { Text(text = "Email Address")},
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

            Column(modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("pinLogin") },
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(text = AnnotatedString("Click here to login with PIN code"),
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                          if (checkPassword(password = password)){
                              navController.navigate("home")
                          }else{
                              Toast.makeText(context,"Incorrect email or password", Toast.LENGTH_LONG).show()
                          }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF00C6CF),
                    contentColor = Color.Black)
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "OR",
                    fontWeight = FontWeight.Bold,

                    )
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { navController.navigate("registerUser") },
                border = BorderStroke(2.dp, Color(0xFF00C6CF)),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp)
            ) {
                Text(text = "Sign up", color = Color(0xFF00C6CF), fontWeight = FontWeight.Bold)
            }

        }
    }

}

fun checkPassword(
    password: MutableState<String>
):Boolean{
    return password.value == "ABCD1233"
}