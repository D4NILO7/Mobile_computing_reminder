package com.example.mobilecomputingexerciseproject.ui.reminderUI

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.MagnifierStyle.Companion.Default
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.ui.uiElements.CreateTopBar
import com.example.mobilecomputingexerciseproject.ui.uiElements.DropdownMenuUi
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateReminder(
    navController: NavController
) {
    val message = remember { mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }
    val mTime = remember { mutableStateOf("") }
    val listItems = arrayOf("High", "Medium", "Low")
    var selectedItem by remember {
        mutableStateOf(listItems[1])
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        CreateTopBar(navController = navController, "Add reminder",
            logOutIcon = false,
            submitButton = checkFields(message.value, mDate.value, mTime.value),
            submitAction = {})

        Column(modifier = Modifier.padding(20.dp)) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = message.value,
                onValueChange = { text -> message.value = text },
                label = { Text(text = "Title", fontSize = 16.sp, color = Color.White) },
                textStyle = androidx.compose.ui.text.TextStyle(Color(0xFF00C6CF), fontSize = 24.sp, fontWeight = FontWeight.Bold )
            )
        }

        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
                //.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {

            Spacer(modifier = Modifier.height(24.dp))
            //PICK REMINDER TIME

            val mContext = LocalContext.current

            // Declaring and initializing a calendar
            val mCalendar = Calendar.getInstance()
            val mHour = mCalendar[Calendar.HOUR_OF_DAY]
            val mMinute = mCalendar[Calendar.MINUTE]

            // Value for storing time as a string

            // Creating a TimePicker dialod
            val mTimePickerDialog = TimePickerDialog(
                mContext,
                { _, mHour: Int, mMinute: Int ->
                    mTime.value = "$mHour:$mMinute"
                }, mHour, mMinute, true
            )

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
            val mDatePickerDialog = DatePickerDialog(
                mContext,
                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                    mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
                }, mYear, mMonth, mDay
            )

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
                    Text(text = mDate.value, fontSize = 24.sp, color = Color(0xFF00C6CF), modifier = Modifier.padding(8.dp))
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
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 24.sp, color = Color(0xFF00C6CF)),
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
            }
            //--------------------------------------------------------

        }
    }
}

fun checkFields(
    title:String?,
    date:String?,
    time:String?
):Boolean{

    if(title!="" && date!="" && time!=""){
        return true
    }
    return false
}