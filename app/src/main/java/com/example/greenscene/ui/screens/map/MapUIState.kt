package com.example.greenscene.ui.screens.map

import com.google.android.gms.maps.model.LatLng

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
)
