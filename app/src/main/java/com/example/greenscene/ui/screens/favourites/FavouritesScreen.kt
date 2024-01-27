package com.example.greenscene.ui.screens.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.greenscene.R

@Composable
fun FavouritesScreen() {
    Column {
        Text(text = stringResource(id = R.string.favorites))
    }
}