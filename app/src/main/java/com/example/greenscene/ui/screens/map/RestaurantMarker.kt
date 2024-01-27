package com.example.greenscene.ui.screens.map

import androidx.compose.runtime.Composable
import com.example.greenscene.model.Restaurant
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState


@Composable
fun RestaurantMarker(restaurant: Restaurant, onClick: (Marker) -> Unit ) {
    val veganPinHue = 149.0F
    val veganOptionsHue = 172.0F
    val vegetarianHue = 193.0F


    val restaurantType = restaurant.type.split(",").map { it.trim() }

    val markerHue = when {
        restaurantType.contains("vegan-options") -> veganOptionsHue
        restaurantType.contains("vegan") -> veganPinHue
        restaurantType.contains("vegetarian") -> vegetarianHue
        else -> BitmapDescriptorFactory.HUE_RED
    }

    Marker(
        title = restaurant.name,
        snippet = restaurant.category,
        state = rememberMarkerState(
            position = LatLng(restaurant.geopoint.latitude, restaurant.geopoint.longitude),
        ),
        icon = BitmapDescriptorFactory.defaultMarker(markerHue),
        onClick = {
            onClick(it)
            return@Marker true
        }
    )
}