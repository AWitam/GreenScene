package com.example.greenscene.ui.screens.profile

data class ProfileUiState(
    val isAnonymousAccount: Boolean = true,
    val name: String? = "",
    val email: String = "",
    val photoUrl: String? = ""
)