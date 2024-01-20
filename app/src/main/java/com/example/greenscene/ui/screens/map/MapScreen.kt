package com.example.greenscene.ui.screens.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@SuppressLint("MissingPermission")
@Composable
fun MapScreen(viewModel: MapViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    val usePreciseLocation = true

    LocationPermissionBox(
        onGranted = {
            Location(
                usePreciseLocation = usePreciseLocation,
                uiState = uiState,
                onCurrentLocationChanged = viewModel::onCurrentLocationChanged,
            )
        },
    )
}

@SuppressLint("MissingPermission")
@Composable
fun Location(
    usePreciseLocation: Boolean,
    uiState: State<MapUIState>,
    onCurrentLocationChanged: (LatLng) -> Unit,

    ) {
    val (currentLocationState, mapProperties, mapUISettings) = uiState.value

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

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
                onCurrentLocationChanged(
                    LatLng(
                        fetchedLocation.latitude, fetchedLocation.longitude
                    )
                )

            }
        }
    }

    when (currentLocationState) {
        is CurrentLocationState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onBackground)
            }
        }

        is CurrentLocationState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error")
            }
        }

        is CurrentLocationState.DataLoaded -> {
            currentLocationState.let {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(it.data.location, 17f)
                }

                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUISettings,
                    properties = mapProperties,
                )
            }
        }
    }

}


