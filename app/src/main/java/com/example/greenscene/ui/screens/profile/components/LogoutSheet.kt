package com.example.greenscene.ui.screens.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenscene.R
import com.example.greenscene.ui.components.ButtonSize
import com.example.greenscene.ui.components.Divider
import com.example.greenscene.ui.components.FilledButton
import com.example.greenscene.ui.components.TonalButton
import com.example.greenscene.ui.theme.GreenSceneTheme



@Composable
fun LogoutSheetContent(
    onLogOut: () -> Unit, onDismissClick: () -> Unit
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
                text = stringResource(R.string.log_out_title),
                color = MaterialTheme.colorScheme.error
            )
            Divider()
            Text(
                text = stringResource(R.string.log_out_description),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(24.dp))
            FilledButton(
                text = R.string.log_out_confirm,
                action = onLogOut,
                size = ButtonSize.LARGE,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            TonalButton(
                text = R.string.log_out_cancel,
                action = onDismissClick,
                size = ButtonSize.LARGE,
                modifier = Modifier.fillMaxWidth()
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetPreview() {
    GreenSceneTheme {
        LogoutSheetContent(onDismissClick = { }, onLogOut = {})
    }
}
