package com.example.greenscene.ui.views.sign_up

import androidx.compose.runtime.mutableStateOf
import com.example.greenscene.PROFILE_SCREEN
import com.example.greenscene.SIGN_UP_SCREEN
import com.example.greenscene.common.extensions.isValidEmail
import com.example.greenscene.common.extensions.isValidPassword
import com.example.greenscene.common.extensions.passwordMatches
import com.example.greenscene.common.snackbar.SnackbarManager
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.views.GreenSceneViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.greenscene.R.string as AppText

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
) : GreenSceneViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            accountService.linkAccount(email, password)
            openAndPopUp(PROFILE_SCREEN, SIGN_UP_SCREEN)
        }
    }
}
