package com.example.greenscene

import com.example.greenscene.model.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appPreferences: AppPreferences
): GreenSceneViewModel() {

    private val _themeState = MutableStateFlow(appPreferences.theme)
    val themeState = _themeState

    fun onThemeChanged(theme: String) {
        _themeState.value = theme
    }
}