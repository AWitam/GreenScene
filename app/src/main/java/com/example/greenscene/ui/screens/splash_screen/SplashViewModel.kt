package com.example.greenscene.ui.screens.splash_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.greenscene.MAP_SCREEN
import com.example.greenscene.SPLASH_SCREEN
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.screens.GreenSceneViewModel
import com.google.firebase.auth.FirebaseAuthException
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
            } catch (ex: FirebaseAuthException) {
                Log.d("idk", "createAnonymousAccount: ${ex.message}")
                showError.value = true
                throw ex
            } catch (ex: Exception) {
                Log.d("idk", "createAnonymousAccount: ${ex.message}")
                showError.value = true
                throw ex
            }

            openAndPopUp(MAP_SCREEN, SPLASH_SCREEN)
        }
    }

}