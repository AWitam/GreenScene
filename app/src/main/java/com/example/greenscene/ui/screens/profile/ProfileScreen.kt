package com.example.greenscene.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.greenscene.PROFILE_SCREEN
import com.example.greenscene.ui.components.BasicToolbar
import com.example.greenscene.ui.components.Divider
import com.example.greenscene.ui.screens.profile.components.AppearanceSheet
import com.example.greenscene.ui.screens.profile.components.LogoutSheetContent
import com.example.greenscene.ui.screens.profile.components.ProfileInfo
import com.example.greenscene.ui.screens.profile.components.WelcomeCard
import com.example.greenscene.ui.screens.profile.components.ProfileOptionsList
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.R.string as AppText


@ExperimentalMaterialApi
@Composable
fun ProfileScreen(
    onThemeChanged: (String) -> Unit,
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val authState by viewModel.authState.collectAsState(initial = AuthUiState())
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        BasicToolbar(
            title = AppText.profile
        )
    }) { innerPadding ->
        ProfileViewContent(
            onLoginClick = { viewModel.onLoginClick(openScreen) },
            authState = authState,
            uiState = uiState,
            onSignUpClick = { viewModel.onSignUpClick(openScreen) },
            onLogoutClick = { viewModel.onLogOutClick() },
            onDismissBottomSheet = { viewModel.onDismissBottomSheet() },
            onProfileOptionClick = { option -> viewModel.onProfileOptionsItemClicked(option) },
            onSelectedAppearanceChanged = { appearance ->
                onThemeChanged(appearance.id)
                viewModel.onSelectedAppearanceChanged(appearance)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileViewContent(
    modifier: Modifier = Modifier,
    authState: AuthUiState,
    uiState: ProfileUiState,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDismissBottomSheet: () -> Unit = {},
    onSelectedAppearanceChanged: (AppearanceOptionsItem) -> Unit,
    onProfileOptionClick: (ProfileOptionsItem) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        if (authState.isAnonymousAccount) {
            WelcomeCard(
                onLoginClick = onLoginClick, onSignUpClick = onSignUpClick
            )
        }

        if (!authState.isAnonymousAccount) {
            ProfileInfo(authState)
            Divider()
            ProfileOptionsList(
                items = uiState.profileOptions, onItemClick = onProfileOptionClick
            )

            if (uiState.selectedOption != null) {
                ModalBottomSheet(onDismissRequest = onDismissBottomSheet) {
                    when (uiState.selectedOption) {
                        ProfileOptionsItem.Logout -> {
                            LogoutSheetContent(
                                onLogOut = onLogoutClick, onDismissClick = onDismissBottomSheet
                            )
                        }

                        ProfileOptionsItem.Appearance -> {
                            AppearanceSheet(
                                onSelectAppearance = onSelectedAppearanceChanged,
                                appearanceOptions = uiState.appearanceOptions,
                                selectedOption = uiState.selectedAppearanceOption,
                            )
                        }

                        ProfileOptionsItem.EditProfile -> {
                            // TODO
                        }
                    }
                }

            }
        }
    }
}
