package com.example.mobilecomputingexerciseproject.reminder

data class Reminder(
    val message: String = "",
    val locationX: Double = 0.0,
    val locationY: Double = 0.0,
    val creationTime: java.util.Date= java.util.Date(),
    val reminderTime: java.util.Date = java.util.Date(),
    val userId:String = "",
    val reminderSeen: Boolean = false,
    val reminderPriority: String = "",
    val reminderId: String = ""
)