package com.example.greenscene.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.greenscene.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int) {
    TopAppBar(title = { Text(stringResource(title)) })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWithNavigationIcon(@StringRes title: Int, onBackIconClicked: () -> Unit) {
    TopAppBar(
        title = { Text(stringResource(id = title)) },
        navigationIcon = {
            IconButton(onClick = {
                onBackIconClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(AppText.arrow_back_content_description)
                )
            }
        })
}
