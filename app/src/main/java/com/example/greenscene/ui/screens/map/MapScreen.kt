package com.example.greenscene.ui.screens.map

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@SuppressLint("MissingPermission")
@Composable
fun MapScreen() {
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    LocationPermissionBox(
        permissions = permissions,
        onGranted = {
            Location(true)
        },
    )
}

@SuppressLint("MissingPermission")
@Composable
fun Location(usePreciseLocation: Boolean) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var location by remember { mutableStateOf<LatLng?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = locationClient) {
        scope.launch(Dispatchers.IO) {

            val priority = if (usePreciseLocation) {
                Priority.PRIORITY_HIGH_ACCURACY
            } else {
                Priority.PRIORITY_BALANCED_POWER_ACCURACY
            }
            val currentLocationResult = locationClient.getCurrentLocation(
                priority, CancellationTokenSource().token
            ).await()
            currentLocationResult?.let { fetchedLocation ->
                location = LatLng(fetchedLocation.latitude, fetchedLocation.longitude)
                isLoading = false
            }
        }
    }

    val uiSettings by remember { mutableStateOf(MapUiSettings(myLocationButtonEnabled = true)) }
    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL, isMyLocationEnabled = true))
    }

    if (isLoading || location == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
        }
    } else {
        location?.let {
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(it, 17f)
            }

            GoogleMap(
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings,
                properties = mapProperties,
            )
        }
    }
}


