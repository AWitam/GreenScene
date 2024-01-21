package com.example.greenscene.model.service.impl

import android.util.Log
import com.example.greenscene.model.Restaurant
import com.example.greenscene.model.RestaurantDetails
import com.example.greenscene.model.RestaurantReview
import com.example.greenscene.model.service.RestaurantService
import com.firebase.geofire.GeoFireUtils
import com.firebase.geofire.GeoLocation
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import javax.inject.Inject

class RestaurantServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : RestaurantService {

    override suspend fun getNearbyRestaurants(
        currentLocation: LatLng,
        radius: Double,
        onSuccess: (List<Restaurant>) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        val center = GeoLocation(currentLocation.latitude, currentLocation.longitude)

        val bounds = GeoFireUtils.getGeoHashQueryBounds(center, radius)
        val tasks: MutableList<Task<QuerySnapshot>> = ArrayList()
        for (b in bounds) {
            val q = firestore.collection(RESTAURANTS_COLLECTION)
                .orderBy("geohash")
                .startAt(b.startHash)
                .endAt(b.endHash)
            tasks.add(q.get())
        }

        Tasks.whenAllComplete(tasks)
            .addOnCompleteListener {
                val matchingDocs: MutableList<DocumentSnapshot> = ArrayList()
                for (task in tasks) {
                    val snap = task.result
                    for (doc in snap!!.documents) {

                        Log.d("RestaurantService", "Doc: $doc")
                        val geoPoint = doc.getGeoPoint("geopoint")

                        val lat = geoPoint?.latitude
                        val long = geoPoint?.longitude

                        if(lat == null || long == null) {
                            continue
                        }
                        val docLocation = GeoLocation(lat, long)
                        val distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center)
                        if (distanceInM <= radius) {
                            matchingDocs.add(doc)
                        }
                    }
                }

                val restaurants = matchingDocs.map { doc ->
                    doc.toObject<Restaurant>()
                }

                onSuccess(restaurants.filterNotNull())
            }.addOnFailureListener(onError)
    }

    override suspend fun getRestaurantDetails(
        restaurantId: String,
        onSuccess: (Boolean, RestaurantDetails?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getRestaurantReviews(
        restaurantId: String,
        onSuccess: (Boolean, List<RestaurantReview>?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun addRestaurantReview(
        restaurantId: String,
        review: RestaurantReview,
        onSuccess: (Boolean) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }


    companion object {
        private const val RESTAURANTS_COLLECTION = "restaurants"
        private const val RESTAURANT_ID_FIELD = "id"
    }

}