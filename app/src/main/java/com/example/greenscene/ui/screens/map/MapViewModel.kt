package com.example.greenscene.ui.screens.map

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.greenscene.model.service.RestaurantService
import com.example.greenscene.ui.screens.GreenSceneViewModel
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val restaurantService: RestaurantService
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

    fun getNearbyRestaurants() {
        launchCatching {
            when (_uiState.value.currentLocation) {
                is CurrentLocationState.DataLoaded -> {
                    val location =
                        (_uiState.value.currentLocation as CurrentLocationState.DataLoaded).data.location

                    viewModelScope.launch {
                        restaurantService.getNearbyRestaurants(currentLocation = location,
                            radius = 50 * 1000.0,
                            onError = {},
                            onSuccess = { restaurants ->
                                _uiState.value = _uiState.value.copy(
                                    restaurants = restaurants
                                )
                            }
                        )
                    }
                }

                else -> {
                    Log.d("MapViewModel", "No location")
                }
            }
        }
    }
}