package com.example.mobilecomputingexerciseproject.ui.data.entity

import java.util.*

data class Reminder(
    val reminderId: Long,
    val reminderTitle: String,
    val reminderDate: Date?,
    val reminderCategory: String
)