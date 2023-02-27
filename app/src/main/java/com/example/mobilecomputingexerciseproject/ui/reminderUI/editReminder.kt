package com.example.mobilecomputingexerciseproject.ui.reminderUI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.reminder.Reminder
import com.example.mobilecomputingexerciseproject.ui.profile.ProfileClass
import com.example.mobilecomputingexerciseproject.ui.uiElements.CreateTopBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EditReminder(
    navController: NavController,
    reminderId: String
) {

    var reminder: Reminder? = null
    val message = remember { mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }
    val mTime = remember { mutableStateOf("") }
    val listItems = arrayOf("High", "Medium", "Low")
    var selectedItem by remember {
        mutableStateOf(listItems[1])
    }
    var fetched = remember { mutableStateOf(false) }

    val db = Firebase.firestore
    val docRef = db.collection("reminders").document(reminderId)
    val formatter: DateFormat = SimpleDateFormat("dd-MM-yyyy,HH:mm:ss")

    if (!fetched.value) {
        docRef.get()
            .addOnSuccessListener { snapShots ->
                reminder = snapShots.toObject(Reminder::class.java)
                message.value = reminder?.message.toString()
                if (reminder?.reminderPriority.toString() == "High") {
                    selectedItem = listItems[0]
                } else if (reminder?.reminderPriority.toString() == "Medium") {
                    selectedItem = listItems[1]
                } else {
                    selectedItem = listItems[2]
                }
                val dateHelper = formatter.format(reminder?.reminderTime)
                mDate.value = dateHelper.substring(0, dateHelper.indexOf(","))
                mTime.value =
                    dateHelper.substring(dateHelper.indexOf(",") + 1, dateHelper.length - 3)
                fetched.value = true
            }
    }


    //PICK REMINDER TIME

    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string

    // Creating a TimePicker dialod
    var mHourString = ""
    var mMinuteString = ""
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            if (mHour < 10) {
                mHourString = "0$mHour"
            } else {
                mHourString = "$mHour"
            }

            if (mMinute < 10) {
                mMinuteString = "0$mMinute"
            } else {
                mMinuteString = "$mMinute"
            }

            mTime.value = "$mHourString:$mMinuteString"
        }, mHour, mMinute, false
    )

    // DATE PICKER

    // Fetching current year, month and day
    val mYear: Int = mCalendar.get(Calendar.YEAR)
    val mMonth: Int = mCalendar.get(Calendar.MONTH)
    val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    var mMonthString = ""
    var mDayOfMonthString = ""

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            if (mMonth < 10) {
                mMonthString = "0${mMonth + 1}"
            } else {
                mMonthString = "${mMonth + 1}"
            }

            if (mDayOfMonth < 10) {
                mDayOfMonthString = "0$mDayOfMonth"
            } else {
                mDayOfMonthString = "$mDayOfMonth"
            }
            mDate.value = "$mDayOfMonthString-${mMonthString}-$mYear"
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.fillMaxHeight()) {
        CreateTopBar(navController = navController, "Edit reminder",
            logOutIcon = false,
            submitButton = checkFields(message.value, mDate.value, mTime.value),
            submitAction = {
                submitReminder(
                    message = message.value,
                    reminderPriority = selectedItem,
                    reminderTime = createReminderDate(mDate.value, mTime.value),
                    navController = navController,
                    reminderId = reminderId
                )
            })

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = message.value,
                onValueChange = { text -> message.value = text },
                label = { Text(text = "Title", fontSize = 16.sp, color = Color.White) },
                textStyle = TextStyle(
                    Color(0xFF00C6CF),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    mTimePickerDialog.show()
                },
                border = BorderStroke(1.dp, Color(0xFF00C6CF)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Time", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    Spacer(Modifier.weight(1f))
                    Text(text = mTime.value, fontSize = 24.sp, modifier = Modifier.padding(8.dp))
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    mDatePickerDialog.show()
                },
                border = BorderStroke(1.dp, Color(0xFF00C6CF)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Date", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = mDate.value,
                        fontSize = 24.sp,
                        color = Color(0xFF00C6CF),
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))
            //--------------------------------------------------------

            var expanded by remember {
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {


                // the box
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                ) {

                    // text field
                    TextField(
                        value = selectedItem,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(text = "Priority", fontSize = 16.sp, color = Color.White) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        textStyle = TextStyle(fontSize = 24.sp, color = Color(0xFF00C6CF)),
                        modifier = Modifier.fillMaxWidth()

                    )

                    // menu
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listItems.forEach { selectedOption ->
                            // menu item
                            DropdownMenuItem(onClick = {
                                selectedItem = selectedOption
                                expanded = false
                            }) {
                                Text(text = selectedOption, fontSize = 24.sp)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {

                        db.collection("reminders").document(reminderId)
                            .delete()
                            .addOnSuccessListener {
                                Log.d(
                                    ContentValues.TAG,
                                    "DocumentSnapshot successfully deleted!"
                                )
                            }
                            .addOnFailureListener { e ->
                                Log.w(
                                    ContentValues.TAG,
                                    "Error deleting document",
                                    e
                                )
                            }

                        navController.navigate("home")

                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(20.dp)),
                    contentPadding = PaddingValues(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFDB3B21)
                    )
                ) {
                    Text(text = "Delete")
                }


            }
        }
    }

}


private fun checkFields(
    title: String?,
    date: String?,
    time: String?
): Boolean {

    if (title != "" && date != "" && time != "") {
        return true
    }
    return false
}


private fun createReminderDate(
    date: String,
    hours: String
): Date {

    val testDate = "${date},${hours}:00"
    val formatter: DateFormat = SimpleDateFormat("dd-MM-yyyy,HH:mm:ss")
    val resultDate: Date = formatter.parse(testDate)
    println(resultDate)
    return resultDate
}


private fun submitReminder(
    message: String,
    reminderTime: java.util.Date,
    reminderPriority: String,
    navController: NavController,
    reminderId: String
) {

    var fAuth = FirebaseAuth.getInstance()

    val reminder = Reminder(
        message = message,
        locationX = "",
        locationY = "",
        creationTime = Date(),
        reminderTime = reminderTime,
        userId = fAuth.uid.toString(),
        reminderSeen = false,
        reminderPriority = reminderPriority,
        reminderId = ""
    )

    val db = Firebase.firestore
    db.collection("reminders").document(reminderId).set(reminder)
        .addOnSuccessListener {
            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${fAuth.uid}")
            navController.navigate("home")
        }
        .addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Error adding document", e)
        }


}