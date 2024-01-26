package com.example.greenscene.ui.screens.profile.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenscene.R
import com.example.greenscene.ui.components.Divider
import com.example.greenscene.ui.screens.profile.AppearanceOptionsItem
import com.example.greenscene.ui.theme.GreenSceneTheme


@Composable
fun AppearanceSheet(
    selectedOption: AppearanceOptionsItem?,
    appearanceOptions: List<AppearanceOptionsItem>,
    onSelectAppearance: (AppearanceOptionsItem) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 120.dp, start = 24.dp, end = 24.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = stringResource(R.string.appearance),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.appearance_description),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
            Divider()
        }
        Column(horizontalAlignment = Alignment.Start) {

            appearanceOptions.forEach { appearanceOptionsItem ->
                val color = if (appearanceOptionsItem == selectedOption) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectAppearance(appearanceOptionsItem) }
                    .padding(vertical = 16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = stringResource(appearanceOptionsItem.textId),
                        color = color,
                        style = MaterialTheme.typography.titleMedium
                    )

                    if (appearanceOptionsItem == selectedOption) {
                        Icon(
                            painter = painterResource(id = R.drawable.check),
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier
                                .width(24.dp)
                                .height(24.dp)
                        )

                    }

                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppearanceSheetPreview() {
    GreenSceneTheme(useDarkTheme = true) {
        AppearanceSheet(
            selectedOption = AppearanceOptionsItem.Light,
            onSelectAppearance = { },
            appearanceOptions = listOf(
                AppearanceOptionsItem.SystemDefault,
                AppearanceOptionsItem.Light,
                AppearanceOptionsItem.Dark
            )
        )
    }
}
