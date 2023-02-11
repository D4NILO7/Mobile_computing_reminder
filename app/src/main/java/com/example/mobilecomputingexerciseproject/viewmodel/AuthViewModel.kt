package com.example.mobilecomputingexerciseproject.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.mobilecomputingexerciseproject.auth.FirebaseAuthRepo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel: ViewModel(){

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _userLoginStatus = MutableStateFlow<UserLoginStatus?>(null)
    val userLoginStatus = _userLoginStatus.asStateFlow()

    private val _userSignUpStatus = MutableStateFlow<UserSignUpStatus?>(null)
    val userSignUpStatus = _userSignUpStatus.asStateFlow()

    init {
        createAccount("danieltisza23@gmail.com", "123456789")
    }

    fun performLogin(email: String, password: String){
        FirebaseAuthRepo.login(
            firebaseAuth,
            email,
            password,
            onSuccess = {
                _userLoginStatus.value = UserLoginStatus.Successful
            },
            onFailure = {
                _userLoginStatus.value = UserLoginStatus.Failure(it)
            }
        )
    }

    fun createAccount(email: String, password: String){
        FirebaseAuthRepo.signUp(
            firebaseAuth,
            email,
            password,
            onSuccess = {
                _userSignUpStatus.value = UserSignUpStatus.Successful
            },
            onFailure = {
                _userSignUpStatus.value = UserSignUpStatus.Failure(it)
            }
        )
    }
}

sealed class UserLoginStatus{
    object Successful: UserLoginStatus()
    class Failure(val exception: Exception?): UserLoginStatus()
}

sealed class UserSignUpStatus{
    object Successful: UserSignUpStatus()
    class Failure(val exception: Exception?): UserSignUpStatus()
}