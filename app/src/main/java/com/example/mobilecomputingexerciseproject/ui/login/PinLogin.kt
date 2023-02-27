package com.example.mobilecomputingexerciseproject.ui.login

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.MainActivity
import com.example.mobilecomputingexerciseproject.R

@Composable
fun PinLogin (
    navController: NavController
){
    Surface(modifier = Modifier.fillMaxSize()) {
        var otpValue by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center

        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Enter PIN code",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = otpValue,
                onValueChange = {
                    if (it.length <= 5) {
                        otpValue = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                decorationBox = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        repeat(5) { index ->
                            val char = when {
                                index >= otpValue.length -> ""
                                else -> "*"
                            }
                            Text(
                                modifier = Modifier
                                    .width(40.dp)
                                    .border(
                                        1.dp,
                                        Color(0xFF00C6CF),
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(2.dp),
                                text = char,
                                textAlign = TextAlign.Center,
                                fontSize = 32.sp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            )



            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape( corner = CornerSize(20.dp)),
                contentPadding = PaddingValues(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF00C6CF),
                    contentColor = Color.Black)
            ) {
                Text(text = "Login")
            }
        }
    }
}

/*    val context = LocalContext.current
    val notificationManager = NotificationManagerCompat.from(context)
    val channelId = "MyChannelId"
    val channelName = "MyChannelName"
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    val notificationBuilder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle("Reminder")
        .setContentText("Your reminder message")
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    val notificationId = 1*/