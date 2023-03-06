package com.example.mobilecomputingexerciseproject.ui.maps

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobilecomputingexerciseproject.ui.home.categoryReminder.CategoryReminderViewModel
import com.example.mobilecomputingexerciseproject.ui.home.categoryReminder.ReminderListItem
import com.example.mobilecomputingexerciseproject.util.rememberMapViewWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun NearbyReminders(
    navController: NavController
) {
    val mapView = rememberMapViewWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    val viewModel: CategoryReminderViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()
    var kocog = remember { mutableStateOf(2) }
    kocog.value = 15
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView({ mapView }) { mapView ->
            coroutineScope.launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
                val location = LatLng(65.06, 25.47)
                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(location, 12f)
                )
                for (reminder in viewState.reminders){
                    println(reminder.locationX.toString())
                    val snippet = String.format(
                        Locale.getDefault(),
                        "Lat: %1$.2f, Lng: %2$.2f",
                        reminder.locationX,
                        reminder.locationY
                    )
                    map.addMarker(
                        MarkerOptions().position(LatLng(reminder.locationX, reminder.locationY)).title(reminder.message).snippet(snippet)
                    )
                }
            }
        }
    }
}