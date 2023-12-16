package com.example.greenscene.ui.views.profile

import com.example.greenscene.LOG_IN_SCREEN
import com.example.greenscene.SIGN_UP_SCREEN
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.views.GreenSceneViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : GreenSceneViewModel() {
    val uiState = ProfileUiState()

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOG_IN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            // todo: replace with restart app and go to splash screen
        }
    }


}