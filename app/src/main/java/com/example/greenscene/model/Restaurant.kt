package com.example.greenscene.model

import com.google.firebase.firestore.GeoPoint


data class Restaurant(
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val geopoint: GeoPoint = GeoPoint(0.0, 0.0),
    val geohash: String = "",
    val rating: Double = 0.0,
    val photoUrl: String = "",
    val priceLevel: Int = 0,
    val openingHours: String = "",
    val category: String = "",
    val type: String = "",
)

data class RestaurantDetails(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val address: String = "",
    val geopoint: GeoPoint = GeoPoint(0.0, 0.0),
    val geohash: String = "",
    val rating: Double = 0.0,
    val photoUrls: List<String> = emptyList(),
    val priceLevel: Int = 0,
    val openingHours: String = "",
    val phoneNumber: String = "",
    val website: String = "",
    val category: String = "",
    val type: String = "",
)


data class RestaurantReview(
    val id: String = "",
    val restaurantId: String = "",
    val author: String = "",
    val authorPhotoUrl: String = "",
    val rating: Double = 0.0,
    val text: String = "",
    val time: Long = 0L
)
