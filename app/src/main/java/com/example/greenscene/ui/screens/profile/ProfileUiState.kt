package com.example.greenscene.ui.screens.profile

import androidx.annotation.StringRes
import com.example.greenscene.R



sealed class ProfileOptionsItem(val id: String, @StringRes val textId:Int, val iconId: Int) {
    object EditProfile : ProfileOptionsItem(id = "edit_profile", textId = R.string.edit_profile, iconId = R.drawable.profile)
    object Appearance : ProfileOptionsItem(id = "appearance", textId = R.string.appearance, iconId = R.drawable.appearance)
    object Logout : ProfileOptionsItem(id = "logout", textId = R.string.sign_out, iconId = R.drawable.logout)
}


sealed class AppearanceOptionsItem(val id: String, @StringRes val textId:Int) {
    object SystemDefault : AppearanceOptionsItem(id = "system_default", textId = R.string.system_default)
    object Light : AppearanceOptionsItem(id = "light", textId = R.string.light_mode)
    object Dark : AppearanceOptionsItem(id = "dark", textId = R.string.dark_mode)
}
data class ProfileUiState(
    val profileOptions: List<ProfileOptionsItem> = listOf(
        ProfileOptionsItem.EditProfile,
        ProfileOptionsItem.Appearance,
        ProfileOptionsItem.Logout
    ),
    val selectedOption: ProfileOptionsItem? = null,
    val appearanceOptions: List<AppearanceOptionsItem> = listOf(
        AppearanceOptionsItem.SystemDefault,
        AppearanceOptionsItem.Light,
        AppearanceOptionsItem.Dark
    ),
    val selectedAppearanceOption: AppearanceOptionsItem? = AppearanceOptionsItem.SystemDefault
)
