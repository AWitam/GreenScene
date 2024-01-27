package com.example.greenscene.ui.screens.map

import com.example.greenscene.model.Restaurant
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

sealed class CurrentLocationState {
    object Loading : CurrentLocationState()
    data class Error(val error: Throwable) : CurrentLocationState()
    data class DataLoaded(val data: CurrentLocation) : CurrentLocationState()
}

data class CurrentLocation(
    val location: LatLng,
)

data class MapUIState(
    val currentLocation: CurrentLocationState = CurrentLocationState.Loading,
    val mapProperties: MapProperties = MapProperties(isMyLocationEnabled = true),
    val mapUiSettings: MapUiSettings = MapUiSettings(
        myLocationButtonEnabled = true,
        zoomControlsEnabled = false,
        zoomGesturesEnabled = true
    ),
    val restaurants: List<Restaurant> = emptyList(),
    val selectedRestaurant: Restaurant? = null,
)
