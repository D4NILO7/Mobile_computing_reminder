package com.example.mobilecomputingexerciseproject.ui.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.viewmodel.AuthViewModel
import com.example.mobilecomputingexerciseproject.viewmodel.UserLoginStatus

@Composable
fun Login(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val localContext = LocalContext.current

    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val loginStatus by authViewModel.userLoginStatus.collectAsState()

    LaunchedEffect(key1 = loginStatus){
        when(loginStatus){
            is UserLoginStatus.Failure -> {
            }
            UserLoginStatus.Successful -> {
                navController.navigate("home")
            }
            null -> {
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {



        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                value = email.value,
                onValueChange = { text -> email.value = text },
                label = { Text(text = "Email Address") },
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                onValueChange = { passwordText -> password.value = passwordText },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("pinLogin") },
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = AnnotatedString("Click here to login with PIN code"),
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            LoginFooter(
                onSignInClick = {

                    when {
                        email.value.isBlank() -> {
                            localContext.showToast("Enter your email")
                        }
                        password.value.isBlank() -> {
                            localContext.showToast("Enter your password")
                        }
                        else -> {
                            authViewModel.performLogin(email.value, password.value)
                        }
                    }
                },
                onSignUpClick = {  }
            ) {

            }

        }
    }

}


@Composable
fun LoginFooter(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    function: () -> Unit,
) {

    Button(
        onClick = {
            onSignInClick()
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        contentPadding = PaddingValues(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF00C6CF),
            contentColor = Color.Black
        )
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
        onClick = { onSignUpClick() },
        border = BorderStroke(2.dp, Color(0xFF00C6CF)),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        contentPadding = PaddingValues(20.dp)
    ) {
        Text(text = "Sign up", color = Color(0xFF00C6CF), fontWeight = FontWeight.Bold)
    }
}

private fun Context.showToast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}