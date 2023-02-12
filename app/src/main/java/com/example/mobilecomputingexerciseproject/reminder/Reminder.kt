package com.example.mobilecomputingexerciseproject.reminder

import com.google.type.Date
import com.google.type.DateTime

data class Reminder(
    val message: String = "",
    val locationX: String = "",
    val locationY: String = "",
    val creationTime: java.util.Date= java.util.Date(),
    val reminderTime: java.util.Date = java.util.Date(),
    val userId:String = "",
    val reminderSeen: Boolean = false
)