package com.example.greenscene.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.greenscene.ui.components.BasicToolbar
import com.example.greenscene.ui.components.Divider
import com.example.greenscene.ui.screens.profile.components.ProfileInfo
import com.example.greenscene.ui.screens.profile.components.WelcomeCard
import com.example.greenscene.ui.screens.profile.components.options_list.LogoutLink
import com.example.greenscene.ui.screens.profile.components.options_list.ProfileOptionsList
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.R.string as AppText


@ExperimentalMaterialApi
@Composable
fun ProfileScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    popUp: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState(initial = ProfileUiState())

    Scaffold(topBar = {
        BasicToolbar(
            title = AppText.profile
        )
    }) { innerPadding ->
        ProfileViewContent(
            uiState = uiState,
            onLoginClick = { viewModel.onLoginClick(openScreen) },
            onSignUpClick = { viewModel.onSignUpClick(openScreen) },
            onLogoOutClick = { viewModel.onLogOutClick(restartApp) },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ProfileViewContent(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLogoOutClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        if (uiState.isAnonymousAccount) {
            WelcomeCard(
                onLoginClick = onLoginClick,
                onSignUpClick = onSignUpClick
            )
        } else {
            ProfileInfo(uiState)
            Divider()
            ProfileOptionsList(onLogOutClick = onLogoOutClick)
        }
    }
}


@Preview(showBackground = true)
@ExperimentalMaterialApi
@Composable
fun ProfileScreenPreview() {
    val uiState = ProfileUiState(
        isAnonymousAccount = false,
        name = "John Doe",
        email = "xyz@gmail.com",
        photoUrl = "https://placehold.co/60x60"
    )

    GreenSceneTheme {
        Scaffold(topBar = {
            BasicToolbar(title = AppText.profile)
        }) { innerPadding ->
            ProfileViewContent(
                uiState = uiState,
                onLoginClick = { },
                onSignUpClick = { },
                onLogoOutClick = { },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


