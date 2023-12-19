package com.example.greenscene.ui.screens.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.greenscene.common.extensions.basicButton
import com.example.greenscene.common.extensions.fieldModifier
import com.example.greenscene.ui.components.EmailField
import com.example.greenscene.ui.components.PasswordField
import com.example.greenscene.ui.components.RepeatPasswordField
import com.example.greenscene.ui.components.ToolbarWithNavigationIcon
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.R.string as AppText


@Composable
fun SignUpScreen(
    openAndPopUp: (String, String) -> Unit,
    popUp: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    SignUpScreenContent(
        uiState = uiState,
        onBackIconClicked = { viewModel.onBackIconClicked(popUp) },
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onSignUpClick = { viewModel.onSignUpClick(openAndPopUp) }
    )
}

@Composable
fun SignUpScreenContent(
    modifier: Modifier = Modifier,
    uiState: SignUpUiState,
    onBackIconClicked: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRepeatPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Scaffold(
        topBar = {
            ToolbarWithNavigationIcon(
                title = AppText.create_account,
                onBackIconClicked = { onBackIconClicked() })
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailField(uiState.email, onEmailChange, Modifier.fieldModifier())
            PasswordField(uiState.password, onPasswordChange, Modifier.fieldModifier())
            RepeatPasswordField(
                uiState.repeatPassword,
                onRepeatPasswordChange,
                Modifier.fieldModifier()
            )

            Button(onClick = { onSignUpClick() }, Modifier.basicButton()) {
                Text(text = stringResource(id = AppText.create_account))
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val uiState = SignUpUiState(
        email = "email@test.com"
    )

    GreenSceneTheme {
        SignUpScreenContent(
            uiState = uiState,
            onBackIconClicked = { },
            onEmailChange = { },
            onPasswordChange = { },
            onRepeatPasswordChange = { },
            onSignUpClick = { }
        )
    }
}
