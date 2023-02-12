package com.example.mobilecomputingexerciseproject.ui.login

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.ui.profile.ProfileClass
import com.example.mobilecomputingexerciseproject.viewmodel.AuthViewModel
import com.example.mobilecomputingexerciseproject.viewmodel.UserLoginStatus
import com.example.mobilecomputingexerciseproject.viewmodel.UserSignUpStatus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun RegisterUser(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val username = remember { mutableStateOf("") }
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    var otpValue by remember {
        mutableStateOf("")
    }

    val db = Firebase.firestore
    val user = ProfileClass()


    val signUpStatus by authViewModel.userSignUpStatus.collectAsState()
    var fAuth = FirebaseAuth.getInstance()

    LaunchedEffect(key1 = signUpStatus){
        when(signUpStatus){
            is UserSignUpStatus.Failure -> {
            }
            UserSignUpStatus.Successful -> {
                user.userName = username.value
                user.firstName = firstName.value
                user.lastName = lastName.value
                user.email = email.value
                db.collection("users").document(fAuth.uid.toString()).set(user)
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot added with ID: ${fAuth.uid}")
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
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
            verticalArrangement = Arrangement.Center,

            ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                onValueChange = { text -> firstName.value = text },
                label = { Text(text = "First Name") },
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = lastName.value,
                onValueChange = { text -> lastName.value = text },
                label = { Text(text = "Last Name") },
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username.value,
                onValueChange = { text -> username.value = text },
                label = { Text(text = "Username") },
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = email.value,
                onValueChange = { text -> email.value = text },
                label = { Text(text = "Email Address") },
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password.value,
                onValueChange = { passwordText -> password.value = passwordText },
                label = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = confirmPassword.value,
                onValueChange = { passwordText -> confirmPassword.value = passwordText },
                label = { Text(text = "Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(corner = CornerSize(20.dp))
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "PIN code")
            }

            Spacer(modifier = Modifier.height(24.dp))

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = otpValue,
                onValueChange = {
                    if (it.length <= 5) {
                        otpValue = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(5) { index ->
                            val char = when {
                                index >= otpValue.length -> ""
                                else -> otpValue[index].toString()
                            }
                            Text(
                                modifier = Modifier
                                    .width(40.dp)
                                    .border(
                                        2.dp,
                                        Color(0xFF00C6CF),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(2.dp),
                                text = char,
                                textAlign = TextAlign.Center,
                                fontSize = 32.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )



            Spacer(modifier = Modifier.height(40.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    if (checkForm(
                            emailAddress = email, username = username,
                            firstname = firstName, lastname = lastName,
                            pinCode = otpValue, password = password,
                            confirmPassword = confirmPassword
                        )
                    ) {
                        if (checkPassword(password = password, confirmPassword = confirmPassword)) {
                            authViewModel.createAccount(email.value, password.value)
                        } else {
                            Toast.makeText(context, "Passwords are not matching", Toast.LENGTH_LONG)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Please make sure all fields are filled",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF00C6CF),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "Confirm Registration")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "OR",
                    fontWeight = FontWeight.Bold,

                    )
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { navController.navigate("login") },
                border = BorderStroke(1.dp, Color(0xFF00C6CF)),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp)
            ) {
                Text(text = "Login", color = Color(0xFF00C6CF), fontWeight = FontWeight.Bold)
            }
        }
    }
}


fun checkForm(
    emailAddress: MutableState<String>,
    username: MutableState<String>,
    firstname: MutableState<String>,
    lastname: MutableState<String>,
    pinCode: String,
    password: MutableState<String>,
    confirmPassword: MutableState<String>
): Boolean {
    return (emailAddress.value != "" && username.value != ""
            && firstname.value != "" && lastname.value != ""
            && pinCode != "" && password.value != ""
            && confirmPassword.value != "")
}

fun checkPassword(
    password: MutableState<String>,
    confirmPassword: MutableState<String>
): Boolean {
    if (password.value != confirmPassword.value) {
        return false
    }
    return true
}