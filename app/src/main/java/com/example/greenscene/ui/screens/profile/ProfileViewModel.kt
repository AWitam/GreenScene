package com.example.greenscene.ui.screens.profile

import com.example.greenscene.LOG_IN_SCREEN
import com.example.greenscene.SIGN_UP_SCREEN
import com.example.greenscene.model.AppPreferences
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.GreenSceneViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService,
    private val appPreference: AppPreferences,
) : GreenSceneViewModel() {
    val authState = accountService.currentUser.map {
        AuthUiState(
            isAnonymousAccount = it.isAnonymous,
            name = it.name,
            email = it.email,
            photoUrl = it.photoUrl
        )
    }

    private var _uiState = MutableStateFlow(
        ProfileUiState(
            selectedAppearanceOption = when (appPreference.theme) {
                "light" -> AppearanceOptionsItem.Light
                "dark" -> AppearanceOptionsItem.Dark
                else -> AppearanceOptionsItem.SystemDefault
            }
        )
    )
    val uiState get() = _uiState.asStateFlow()


    fun onBackIconClicked(popUp: () -> Unit) {
        popUp()
    }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOG_IN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onLogOutClick() {
        launchCatching {
            accountService.signOut()
            // todo: replace with restart app and go to splash screen
        }
    }

    fun onDismissBottomSheet() {
        _uiState.value = uiState.value.copy(
            selectedOption = null
        )
    }

    fun onProfileOptionsItemClicked(profileOptionsItem: ProfileOptionsItem) {
        _uiState.value = uiState.value.copy(
            selectedOption = profileOptionsItem
        )
    }

    fun onSelectedAppearanceChanged(selectedAppearanceOptionsItem: AppearanceOptionsItem) {

        appPreference.theme = when (selectedAppearanceOptionsItem) {
            AppearanceOptionsItem.Light -> "light"
            AppearanceOptionsItem.Dark -> "dark"
            AppearanceOptionsItem.SystemDefault -> "system_default"
        }

        _uiState.value = uiState.value.copy(
            selectedAppearanceOption = selectedAppearanceOptionsItem
        )
    }
}