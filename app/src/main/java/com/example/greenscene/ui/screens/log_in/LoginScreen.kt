package com.example.greenscene.ui.screens.log_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.greenscene.common.extensions.basicButton
import com.example.greenscene.common.extensions.fieldModifier
import com.example.greenscene.common.extensions.textButton
import com.example.greenscene.ui.components.ButtonSize
import com.example.greenscene.ui.components.EmailField
import com.example.greenscene.ui.components.FilledButton
import com.example.greenscene.ui.components.PasswordField
import com.example.greenscene.ui.components.ToolbarWithNavigationIcon
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.R.string as AppText

@Composable
fun LogInScreen(
    openAndPopUp: (String, String) -> Unit,
    popUp: () -> Unit,
    viewModel: LogInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    LoginScreenContent(
        uiState = uiState,
        onBackIconClicked = { viewModel.onBackIconClicked(popUp) },
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = { viewModel.onSignInClick(openAndPopUp) },
        onForgotPasswordClick = viewModel::onForgotPasswordClick
    )
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onBackIconClicked: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Scaffold(topBar = {
        ToolbarWithNavigationIcon(AppText.login_details, onBackIconClicked)
    }) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailField(
                value = uiState.email,
                onNewValue = onEmailChange,
                modifier = Modifier.fieldModifier()
            )
            PasswordField(
                value = uiState.password,
                onNewValue = onPasswordChange,
                modifier = Modifier.fieldModifier()
            )
            FilledButton(
                text = AppText.log_in,
                action = { onSignInClick() },
                size = ButtonSize.LARGE,
                modifier = Modifier.basicButton()
            )

            TextButton(onClick = { onForgotPasswordClick() }, modifier = Modifier.textButton()) {
                Text(text = stringResource(id = AppText.forgot_password))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GreenSceneTheme(useDarkTheme = true) {
        LoginScreenContent(uiState = LoginUiState(),
            onEmailChange = {},
            onPasswordChange = {},
            onSignInClick = {},
            onForgotPasswordClick = {},
            onBackIconClicked = {})
    }
}
