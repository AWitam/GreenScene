package com.example.greenscene.ui.screens.profile

import androidx.annotation.StringRes
import com.example.greenscene.R



sealed class ProfileOptionsItem(val id: String, @StringRes val textId:Int, val iconId: Int) {
    object EditProfile : ProfileOptionsItem(id = "edit_profile", textId = R.string.edit_profile, iconId = R.drawable.profile)
    object Appearance : ProfileOptionsItem(id = "appearance", textId = R.string.appearance, iconId = R.drawable.appearance)
    object Logout : ProfileOptionsItem(id = "logout", textId = R.string.sign_out, iconId = R.drawable.logout)
}

data class ProfileUiState(
    val profileOptions: List<ProfileOptionsItem> = listOf(
        ProfileOptionsItem.EditProfile,
        ProfileOptionsItem.Appearance,
        ProfileOptionsItem.Logout
    ),
    val selectedOption: ProfileOptionsItem? = null,
)
