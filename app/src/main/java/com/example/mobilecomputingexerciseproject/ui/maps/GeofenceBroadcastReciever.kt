package com.example.mobilecomputingexerciseproject.ui.maps

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.mobilecomputingexerciseproject.R
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent.hasError()) {
            Log.d("Geofence", "Error: ${geofencingEvent.errorCode}")
            return
        }
        val geofenceTransition = geofencingEvent.geofenceTransition
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER || geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            // Trigger the notification
            val notificationManager = ContextCompat.getSystemService(
                context!!,
                NotificationManager::class.java
            ) as NotificationManager
            notificationManager.notify(
                0,
                NotificationCompat.Builder(context, "MyChannelId")
                    .setContentTitle("You've entered the geofence")
                    .setContentText("You are at the specified location")
                    .setSmallIcon(R.drawable.bell)
                    .build()
            )
        }
    }
}