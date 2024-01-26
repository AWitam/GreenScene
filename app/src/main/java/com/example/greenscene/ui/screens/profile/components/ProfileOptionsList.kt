package com.example.greenscene.ui.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.greenscene.ui.components.InlineCard
import com.example.greenscene.ui.screens.profile.ProfileOptionsItem

import com.example.greenscene.ui.theme.GreenSceneTheme

@Composable
fun ProfileOptionsList(
    items: List<ProfileOptionsItem>,
    onItemClick: (ProfileOptionsItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items.forEach { item ->
            val color = when (item) {
                ProfileOptionsItem.Logout -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.onPrimaryContainer
            }
            
            InlineCard(
                title = item.textId,
                icon = item.iconId,
                color = color,
                onClick = {
                    onItemClick(item)
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileOptionsListPreview() {
    GreenSceneTheme(useDarkTheme = false) {
        ProfileOptionsList(
            items = listOf(
                ProfileOptionsItem.EditProfile,
                ProfileOptionsItem.Appearance,
                ProfileOptionsItem.Logout
            ),
            onItemClick = {}
        )
    }
}
