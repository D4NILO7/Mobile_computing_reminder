package com.example.mobilecomputingexerciseproject.ui.profile

class ProfileClass {
    var userName: String? = ""
        set(value) { field = value }
    var firstName: String? = ""
        set(value) { field = value }
    var lastName: String? = ""
        set(value) { field = value }
    var email: String? = ""
        get() = field
        set(value) { field = value }
}