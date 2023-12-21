package com.example.greenscene.ui.screens.profile.components.options_list

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.greenscene.ui.components.InlineCard
import com.example.greenscene.R as AppResources

@Composable
fun EditProfileLink(
    onClick: () -> Unit,
) {
    InlineCard(
        title = AppResources.string.edit_profile,
        icon = AppResources.drawable.profile,
        onClick = { onClick() },
        color = MaterialTheme.colorScheme.onSurface,
    )
}