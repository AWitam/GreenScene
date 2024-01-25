package com.example.greenscene.ui.screens.profile

import android.util.Log
import com.example.greenscene.LOG_IN_SCREEN
import com.example.greenscene.SIGN_UP_SCREEN
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.screens.GreenSceneViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : GreenSceneViewModel() {
    val authState = accountService.currentUser.map {
        AuthUiState(
            isAnonymousAccount = it.isAnonymous,
            name = it.name,
            email = it.email,
            photoUrl = it.photoUrl
        )
    }


    private var _uiState = MutableStateFlow(ProfileUiState())
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
        when (profileOptionsItem) {
            is ProfileOptionsItem.Logout -> {
                _uiState.value = uiState.value.copy(
                    selectedOption = profileOptionsItem
                )
            }

            is ProfileOptionsItem.EditProfile -> {
                Log.d("ProfileViewModel", "EditProfile")
            }

            is ProfileOptionsItem.Appearance -> {
                Log.d("ProfileViewModel", "Appearance")
            }
        }
    }
}