package com.example.greenscene.ui.components


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenscene.R
import com.example.greenscene.common.extensions.card
import com.example.greenscene.ui.theme.GreenSceneTheme


@Composable
fun InlineCard(
    @StringRes title: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .background(MaterialTheme.colorScheme.surface)
        .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .card()
        ) {
            Row(
                Modifier.size(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(icon), contentDescription = "Icon", tint = color
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                stringResource(title), color = color
            )

        }

    }
}

@Preview
@Composable
fun CardEditorPreview() {
    GreenSceneTheme(useDarkTheme = false) {
        InlineCard(
            title = R.string.profile,
            icon = R.drawable.profile,
            onClick = {},
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
        )
    }

}