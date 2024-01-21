package com.example.greenscene.model.service

import com.example.greenscene.model.Restaurant
import com.example.greenscene.model.RestaurantDetails
import com.example.greenscene.model.RestaurantReview
import com.google.android.gms.maps.model.LatLng

interface RestaurantService {

    suspend fun getNearbyRestaurants(
        currentLocation: LatLng,
        radius: Double,
        onSuccess: (List<Restaurant?>) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun getRestaurantDetails(
        restaurantId: String,
        onSuccess: (Boolean, RestaurantDetails?) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun getRestaurantReviews(
        restaurantId: String,
        onSuccess: (Boolean, List<RestaurantReview>?) -> Unit,
        onError: (Throwable) -> Unit
    )

    suspend fun addRestaurantReview(
        restaurantId: String,
        review: RestaurantReview,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    )

}