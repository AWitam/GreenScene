package com.example.greenscene.ui.screens.splash_screen

import androidx.compose.runtime.mutableStateOf
import com.example.greenscene.MAP_SCREEN
import com.example.greenscene.SPLASH_SCREEN
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.GreenSceneViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : GreenSceneViewModel(){
    val showError = mutableStateOf(false)

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        showError.value = false
        if (accountService.hasUser) openAndPopUp(MAP_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: Exception) {
                showError.value = true
                throw ex
            }
            openAndPopUp(MAP_SCREEN, SPLASH_SCREEN)
        }
    }

}