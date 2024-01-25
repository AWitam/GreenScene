//package com.example.greenscene.model.preferences
//
//import android.content.Context
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.preferencesDataStore
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Inject
//import javax.inject.Singleton
//
//private val Context.dataStore by preferencesDataStore("user_preferences")
//
//@Singleton //You can ignore this annotation as return `datastore` from `preferencesDataStore` is singletone
//class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {
//
//    private val userPreferencesDataStore = appContext.dataStore
//
//    suspend fun setFavourite(mode: Int) {
//        userPreferencesDataStore.edit { preferences ->
//            preferences[] = mode
//        }
//    }
//
//    val themeMode: Flow<Int> = userPreferencesDataStore.data.map { preferences ->
//        preferences[Settings.NIGHT_MODE] ?: AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
//    }
//
//}