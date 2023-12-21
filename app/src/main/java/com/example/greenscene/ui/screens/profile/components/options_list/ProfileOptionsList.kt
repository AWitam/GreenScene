package com.example.greenscene.ui.screens.profile.components.options_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.example.greenscene.ui.theme.GreenSceneTheme

@Composable
fun ProfileOptionsList(
    onLogOutClick: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {
        EditProfileLink(onClick = { })
        LogoutLink {
            onLogOutClick()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileOptionsListPreview() {
    GreenSceneTheme {
        ProfileOptionsList(onLogOutClick = { })
    }
}
