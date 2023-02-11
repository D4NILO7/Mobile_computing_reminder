package com.example.mobilecomputingexerciseproject.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthRepo{

    fun login(
        firebaseAuth: FirebaseAuth,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ){
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    onSuccess()
                }else{
                    onFailure(it.exception)
                }
            }
    }

    fun signUp(
        firebaseAuth: FirebaseAuth,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (Exception?) -> Unit
    ){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    onSuccess()
                }else{
                    onFailure(it.exception)
                }
            }
    }
}