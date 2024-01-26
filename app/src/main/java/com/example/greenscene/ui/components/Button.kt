package com.example.greenscene.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenscene.R
import com.example.greenscene.ui.theme.GreenSceneTheme
import androidx.compose.material3.OutlinedButton as MaterialOutlinedButton
import androidx.compose.material3.FilledTonalButton as MaterialTonalButton

enum class ButtonSize {
    SMALL, MEDIUM, LARGE
}

enum class ButtonVariant {
    PRIMARY, SECONDARY, DANGER
}

@Composable
fun FilledButton(
    @StringRes text: Int,
    action: () -> Unit,
    size: ButtonSize = ButtonSize.MEDIUM,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    modifier: Modifier = Modifier
) {
    val containerColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        ButtonVariant.DANGER -> MaterialTheme.colorScheme.error
    }

    val contentColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.onPrimary
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.onSecondary
        ButtonVariant.DANGER -> MaterialTheme.colorScheme.onError
    }

    val shape = RoundedCornerShape(size = 10.dp)

    when (size) {
        ButtonSize.SMALL -> {
            Button(
                onClick = action,
                modifier = modifier.defaultMinSize(
                    minHeight = 16.dp
                ),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = contentColor
                ),
                shape = shape
            ) {
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }

        ButtonSize.MEDIUM -> {
            Button(
                onClick = action,
                modifier = modifier.defaultMinSize(
                    minHeight = 16.dp
                ),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = contentColor
                ),
                shape = shape
            ) {
                Text(

                    text = stringResource(id = text), style = MaterialTheme.typography.labelMedium
                )
            }
        }

        ButtonSize.LARGE -> {
            Button(
                onClick = action,
                modifier = modifier,
                contentPadding = PaddingValues(vertical = 18.dp, horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = contentColor
                ),
                shape = shape
            ) {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.labelLarge,

                    )
            }
        }
    }
}

@Composable
fun OutlinedButton(
    @StringRes text: Int,
    action: () -> Unit,
    size: ButtonSize = ButtonSize.MEDIUM,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    modifier: Modifier = Modifier
) {
    val containerColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primaryContainer
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.primaryContainer
        ButtonVariant.DANGER -> MaterialTheme.colorScheme.primaryContainer
    }


    val color = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        ButtonVariant.DANGER -> MaterialTheme.colorScheme.error
    }

    val shape = RoundedCornerShape(size = 10.dp)

    when (size) {
        ButtonSize.SMALL -> {
            MaterialOutlinedButton(
                onClick = action,
                modifier = modifier.defaultMinSize(
                    minHeight = 16.dp
                ),
                border = BorderStroke(2.dp, color),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = color
                ),
                shape = shape,

                ) {
                Text(
                    text = stringResource(id = text), style = MaterialTheme.typography.labelSmall
                )
            }
        }

        ButtonSize.MEDIUM -> {
            MaterialOutlinedButton(
                onClick = action,
                modifier = modifier.defaultMinSize(
                    minHeight = 16.dp
                ),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp),
                border = BorderStroke(2.dp, color),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = color
                ),
                shape = shape
            ) {
                Text(
                    text = stringResource(id = text), style = MaterialTheme.typography.labelMedium
                )
            }
        }

        ButtonSize.LARGE -> {
            MaterialOutlinedButton(
                onClick = action,
                modifier = modifier,
                contentPadding = PaddingValues(vertical = 18.dp, horizontal = 16.dp),
                border = BorderStroke(2.dp, color),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = color
                ),
                shape = shape,
            ) {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.labelLarge,

                    )
            }
        }
    }
}


@Composable
fun TonalButton(
    @StringRes text: Int,
    action: () -> Unit,
    size: ButtonSize = ButtonSize.MEDIUM,
    variant: ButtonVariant = ButtonVariant.PRIMARY,
    modifier: Modifier = Modifier
) {
    val containerColor = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.secondaryContainer
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondaryContainer
        ButtonVariant.DANGER -> MaterialTheme.colorScheme.secondaryContainer
    }


    val color = when (variant) {
        ButtonVariant.PRIMARY -> MaterialTheme.colorScheme.primary
        ButtonVariant.SECONDARY -> MaterialTheme.colorScheme.secondary
        ButtonVariant.DANGER -> MaterialTheme.colorScheme.error
    }

    val shape = RoundedCornerShape(size = 10.dp)

    when (size) {
        ButtonSize.SMALL -> {
            MaterialTonalButton(
                onClick = action,
                modifier = modifier.defaultMinSize(
                    minHeight = 16.dp
                ),
                contentPadding = PaddingValues(vertical = 4.dp, horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = color
                ),
                shape = shape,

                ) {
                Text(
                    text = stringResource(id = text), style = MaterialTheme.typography.labelSmall
                )
            }
        }

        ButtonSize.MEDIUM -> {
            MaterialTonalButton(
                onClick = action,
                modifier = modifier.defaultMinSize(
                    minHeight = 16.dp
                ),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = color
                ),
                shape = shape
            ) {
                Text(
                    text = stringResource(id = text), style = MaterialTheme.typography.labelMedium
                )
            }
        }

        ButtonSize.LARGE -> {
            MaterialTonalButton(
                onClick = action,
                modifier = modifier,
                contentPadding = PaddingValues(vertical = 18.dp, horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor, contentColor = color
                ),
                shape = shape,
            ) {
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.labelLarge,

                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    GreenSceneTheme(useDarkTheme = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                FilledButton(text = R.string.log_in, action = {}, size = ButtonSize.SMALL)
                FilledButton(text = R.string.log_in, action = {}, size = ButtonSize.MEDIUM)
                FilledButton(text = R.string.log_in, action = {}, size = ButtonSize.LARGE)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                FilledButton(
                    text = R.string.log_in,
                    action = {},
                    size = ButtonSize.SMALL,
                    variant = ButtonVariant.SECONDARY
                )
                FilledButton(
                    text = R.string.log_in,
                    action = {},
                    size = ButtonSize.MEDIUM,
                    variant = ButtonVariant.SECONDARY
                )
                FilledButton(
                    text = R.string.log_in,
                    action = {},
                    size = ButtonSize.LARGE,
                    variant = ButtonVariant.SECONDARY
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                FilledButton(
                    text = R.string.log_in,
                    action = {},
                    size = ButtonSize.SMALL,
                    variant = ButtonVariant.DANGER
                )
                FilledButton(
                    text = R.string.log_in,
                    action = {},
                    size = ButtonSize.MEDIUM,
                    variant = ButtonVariant.DANGER
                )
                FilledButton(
                    text = R.string.log_in,
                    action = {},
                    size = ButtonSize.LARGE,
                    variant = ButtonVariant.DANGER
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                OutlinedButton(text = R.string.log_in, action = {}, size = ButtonSize.SMALL)
                OutlinedButton(text = R.string.log_in, action = {}, size = ButtonSize.MEDIUM)
                OutlinedButton(text = R.string.log_in, action = {}, size = ButtonSize.LARGE)

            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                TonalButton(text = R.string.log_in, action = {}, size = ButtonSize.SMALL)
                TonalButton(text = R.string.log_in, action = {}, size = ButtonSize.MEDIUM)
                TonalButton(text = R.string.log_in, action = {}, size = ButtonSize.LARGE)
            }
        }
    }
}


