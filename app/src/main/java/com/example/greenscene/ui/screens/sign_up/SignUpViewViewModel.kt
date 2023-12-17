package com.example.greenscene.ui.screens.sign_up

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.greenscene.PROFILE_SCREEN
import com.example.greenscene.SIGN_UP_SCREEN
import com.example.greenscene.common.extensions.isValidEmail
import com.example.greenscene.common.extensions.isValidPassword
import com.example.greenscene.common.extensions.passwordMatches
import com.example.greenscene.common.snackbar.SnackbarManager
import com.example.greenscene.model.service.AccountService
import com.example.greenscene.ui.screens.GreenSceneViewModel
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
            Log.d("SignUpViewModel", "onSignUpClick: email is not valid")
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            Log.d("SignUpViewModel", "onSignUpClick: password is not valid")
            SnackbarManager.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            Log.d("SignUpViewModel", "onSignUpClick: passwords do not match")
            SnackbarManager.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            Log.d("SignUpViewModel", "onSignUpClick: linking account")
            accountService.linkAccount(email, password)
            openAndPopUp(PROFILE_SCREEN, SIGN_UP_SCREEN)
        }
    }
}
