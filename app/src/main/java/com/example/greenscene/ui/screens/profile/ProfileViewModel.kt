package com.example.greenscene.ui.screens.profile

import com.example.greenscene.LOG_IN_SCREEN
import com.example.greenscene.SIGN_UP_SCREEN
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.screens.GreenSceneViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : GreenSceneViewModel() {
      val uiState = accountService.currentUser.map {
        ProfileUiState(
            isAnonymousAccount = it.isAnonymous,
            name = it.name,
            email = it.email,
            photoUrl = it.photoUrl
        )
    }


    fun onBackIconClicked(popUp: () -> Unit) {
        popUp()
    }

    fun onLoginClick(openScreen: (String) -> Unit) = openScreen(LOG_IN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onLogOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            // todo: replace with restart app and go to splash screen
        }
    }


}