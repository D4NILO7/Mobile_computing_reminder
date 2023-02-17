package com.example.mobilecomputingexerciseproject.ui.home.categoryReminder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilecomputingexerciseproject.reminder.Reminder
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CategoryReminder(

) {
    val viewModel: CategoryReminderViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        ReminderList(
            list = viewState.reminders
        )

    }
}

@Composable
private fun ReminderList(
    list: List<Reminder>
) {
    LazyColumn(
        contentPadding = PaddingValues(0.dp), verticalArrangement = Arrangement.Center
    ) {
        items(list) { item ->
            ReminderListItem(reminder = item, onClick = {})
        }
    }
}


@Composable
private fun ReminderListItem(
    reminder: Reminder,
    onClick: () -> Unit,
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }) {

        val (divider, column, reminderTitle, reminderDate, icon, reminderHoursAndMinutes) = createRefs()
        Divider(
            Modifier
                .fillMaxWidth()
                .constrainAs(divider) {
                    top.linkTo(parent.top)
                    centerHorizontallyTo(parent)
                    width = Dimension.fillToConstraints
                }
        )

        Column (
            modifier = Modifier.constrainAs(column){
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(reminderHoursAndMinutes.start)
                width = Dimension.value(24.dp)
                height= Dimension.value(64.dp)
            }
                .background(
                    if (reminder.reminderPriority == "Low"){
                        Color(0xFF1AAA55)
                    }else if (reminder.reminderPriority == "Medium"){
                        Color(0xFFFC9403)
                    }else{
                        Color(0xFFDB3B21)
                    }
                )
        ){

        }

        //--------------------------------------------- title
        Text(
            text = reminder.message,
            maxLines = 1,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.constrainAs(reminderTitle){
/*                linkTo(
                    start = reminderHoursAndMinutes.start,
                    end = icon.start,
                    startMargin = 56.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )*/
                //centerHorizontallyTo(parent)
                top.linkTo(parent.top, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
                start.linkTo(reminderHoursAndMinutes.end, margin = 24.dp)
                end.linkTo(icon.start, margin = 24.dp)
                width = Dimension.preferredWrapContent
            }
        )
        //-------------------------------------------- date
        Text(
            text = reminder.reminderTime.formatToString(),
            maxLines = 1,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.constrainAs(reminderDate){
/*                linkTo(
                    start = parent.start,
                    end = reminderTitle.start,
                    startMargin = 24.dp,
                    endMargin = 8.dp,
                    bias = 0f
                )*/
                start.linkTo(column.end, margin = 20.dp)
                top.linkTo(parent.top, margin = 10.dp)
                bottom.linkTo(reminderHoursAndMinutes.top, 10.dp)
                width = Dimension.preferredWrapContent
            }
        )
        //--------------------------------------------- date
        var hours = ""
        if(reminder.reminderTime.hours < 10) {
            hours = "0" + reminder.reminderTime.hours.toString()
        }else{
            hours = reminder.reminderTime.hours.toString()
        }

        var minutes = ""
        if(reminder.reminderTime.minutes < 10) {
            minutes = "0" + reminder.reminderTime.minutes.toString()
        }else{
            minutes = reminder.reminderTime.minutes.toString()
        }
        // reminder.reminderTime.formatToString()
        Text(
            text = when {
                reminder.reminderTime !=null -> {
                    "$hours:$minutes"
                }
                else -> Date().formatToString()
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.constrainAs(reminderHoursAndMinutes){
                //linkTo(
                    //start = parent.start,
                    //end = reminderTitle.start,
                    //startMargin = 8.dp,
                    //endMargin = 16.dp,
                  //  bias = 0f

                //)
                //centerVerticallyTo(reminderTime)
                start.linkTo(column.end, 20.dp)
                top.linkTo(reminderDate.bottom, 6.dp)
                bottom.linkTo(parent.bottom,10.dp)
            }
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(50.dp)
                .padding(6.dp)
                .constrainAs(icon) {
                    top.linkTo(parent.top, 10.dp)
                    bottom.linkTo(parent.bottom, 10.dp)
                    end.linkTo(parent.end)
                }
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "check"
            )
        }
    }
}


private fun Date.formatToString(): String {
    return SimpleDateFormat("MMMM dd", Locale.getDefault()).format(this)
}