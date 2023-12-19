package com.example.greenscene.ui.screens.log_in

import androidx.compose.runtime.mutableStateOf
import com.example.greenscene.MAP_SCREEN
import com.example.greenscene.PROFILE_SCREEN
import com.example.greenscene.common.extensions.isValidEmail
import com.example.greenscene.common.snackbar.SnackbarManager
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.screens.GreenSceneViewModel
import com.example.greenscene.R.string as AppText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val accountService: AccountService
): GreenSceneViewModel() {

    var uiState = mutableStateOf(LoginUiState())
        private set

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun onBackIconClicked(popUp: () -> Unit) {
        popUp()
    }

    fun onEmailChange(email: String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if(!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if(password.isBlank()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(MAP_SCREEN, PROFILE_SCREEN)
        }
    }

    fun onForgotPasswordClick() {
        if(!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }
}