package com.example.greenscene.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.greenscene.common.extensions.card
import com.example.greenscene.common.extensions.spacer
import com.example.greenscene.ui.components.RegularCardEditor
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.R.drawable as AppIcon
import com.example.greenscene.R.string as AppText


@ExperimentalMaterialApi
@Composable
fun ProfileScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(
        initial = ProfileUiState(false)
    )

    ProfileViewContent(
        uiState = uiState,
        onLoginClick = { viewModel.onLoginClick(openScreen) },
        onSignUpClick = { viewModel.onSignUpClick(openScreen) },
        onSignOutClick = { viewModel.onSignOutClick(restartApp) }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileViewContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onSignOutClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.spacer())

        if (uiState.isAnonymousAccount) {
            RegularCardEditor(AppText.sign_in, AppIcon.ic_visibility_on, "", Modifier.card()) {
                onLoginClick()
            }

            RegularCardEditor(
                AppText.create_account,
                AppIcon.ic_visibility_on,
                "",
                Modifier.card()
            ) {
                onSignUpClick()
            }
        } else {
            SignOutCard { onSignOutClick() }
        }
    }
}

@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    RegularCardEditor(AppText.sign_out, AppIcon.ic_visibility_on, "", Modifier.card()) {
        showWarningDialog = true
    }

    if (showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.sign_out_title)) },
            text = { Text(stringResource(AppText.sign_out_description)) },
            dismissButton = {
                Button(onClick = { showWarningDialog = false }) {
                    Text(stringResource(AppText.cancel))
                }
            },
            confirmButton = {
                Button(onClick = {
                    signOut()
                    showWarningDialog = false
                }) {
                    Text(stringResource(AppText.sign_out))
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}

@Preview(showBackground = true)
@ExperimentalMaterialApi
@Composable
fun SettingsScreenPreview() {
    val uiState = ProfileUiState(isAnonymousAccount = false)

    GreenSceneTheme {
        ProfileViewContent(
            uiState = uiState,
            onLoginClick = { },
            onSignUpClick = { },
            onSignOutClick = { },
        )
    }
}
