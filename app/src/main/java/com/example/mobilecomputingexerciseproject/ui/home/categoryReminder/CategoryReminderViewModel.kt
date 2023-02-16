package com.example.mobilecomputingexerciseproject.ui.home.categoryReminder

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilecomputingexerciseproject.reminder.Reminder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CategoryReminderViewModel : ViewModel(){
    private val _state = MutableStateFlow(CategoryReminderViewState())

    val state : StateFlow<CategoryReminderViewState>
            get() = _state

    /*val list = mutableListOf<Reminder>()*/

    init {
        val db = Firebase.firestore
        var fAuth = FirebaseAuth.getInstance()
        val remindersList = mutableListOf<Reminder>()
        var reminder: Reminder? = null

        db.collection("reminders")
            .whereEqualTo("userId",fAuth.uid.toString())
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    reminder = document.toObject(Reminder::class.java)
                    var temporaryReminder = Reminder(
                        reminder?.message.toString(),
                        reminder?.locationX.toString(),
                        reminder?.locationY.toString(),
                        reminder!!.creationTime,
                        reminder!!.reminderTime,
                        reminder?.userId.toString(),
                        reminder!!.reminderSeen,
                        reminder?.reminderPriority.toString()
                    )
                    remindersList.add(temporaryReminder)
                    println(remindersList)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }

                viewModelScope.launch {
                    _state.value = CategoryReminderViewState(
                        reminders = remindersList
                    )
                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

    }
}

data class CategoryReminderViewState(
    val reminders : List<Reminder> = emptyList()
)

