package com.example.greenscene.ui.screens.profile

data class AuthUiState(
    val isAnonymousAccount: Boolean = true,
    val name: String? = "",
    val email: String = "",
    val photoUrl: String? = "",
)