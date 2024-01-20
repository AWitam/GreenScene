package com.example.greenscene.ui.screens.map

import com.example.greenscene.model.service.RestaurantService
import com.example.greenscene.ui.screens.GreenSceneViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
) : GreenSceneViewModel() {
    private var _uiState = MutableStateFlow(MapUIState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.value = MapUIState(
            currentLocation = CurrentLocationState.Loading,
        )
    }

    fun onCurrentLocationChanged(location: LatLng) {
        _uiState.value = _uiState.value.copy(
           currentLocation = CurrentLocationState.DataLoaded(
               CurrentLocation(location)
           )
        )
    }
}