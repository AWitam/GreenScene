package com.example.greenscene.ui.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenscene.R
import com.example.greenscene.ui.components.FilledButton
import com.example.greenscene.ui.components.OutlinedButton
import com.example.greenscene.ui.theme.GreenSceneTheme
import com.example.greenscene.R.string as AppText

@Composable
fun WelcomeCard(
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {


    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp,
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource( R.drawable.welcome_card),
                contentDescription = "Card Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = AppText.welcome_card_title),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = AppText.welcome_card_description),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    text = AppText.log_in,
                    action = { onLoginClick() },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(12.dp))
                FilledButton(
                    text = AppText.sign_up,
                    action = { onSignUpClick() },
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeCardPreview() {
    GreenSceneTheme(useDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WelcomeCard(onLoginClick = { }, onSignUpClick = { })
        }
    }
}