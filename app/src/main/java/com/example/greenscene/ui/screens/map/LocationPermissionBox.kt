package com.example.greenscene.ui.screens.map

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greenscene.R
import com.example.greenscene.ui.components.ButtonSize
import com.example.greenscene.ui.components.FilledButton
import com.example.greenscene.ui.components.OutlinedButton
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState


val locationPermissions = listOf(
    android.Manifest.permission.ACCESS_COARSE_LOCATION,
    android.Manifest.permission.ACCESS_FINE_LOCATION,
)

@Composable
fun LocationPermissionBox(
    modifier: Modifier = Modifier,
    permissions: List<String> = locationPermissions,
    contentAlignment: Alignment = Alignment.TopStart,
    onGranted: @Composable BoxScope.() -> Unit,
) {
    LocationPermissionLauncher(
        modifier,
        permissions = permissions,
        requiredPermissions = permissions,
        contentAlignment,
    ) { onGranted() }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionLauncher(
    modifier: Modifier = Modifier,
    permissions: List<String> = locationPermissions,
    requiredPermissions: List<String> = permissions,
    contentAlignment: Alignment = Alignment.TopStart,
    onGranted: @Composable BoxScope.(List<String>) -> Unit,
) {
    var errorState by remember { mutableStateOf(false) }

    val permissionState = rememberMultiplePermissionsState(permissions = permissions) { map ->
        val rejectedPermissions = map.filterValues { !it }.keys
        errorState = !rejectedPermissions.none { it in requiredPermissions }
    }

    val allRequiredPermissionsGranted =
        permissionState.revokedPermissions.none { it.permission in requiredPermissions }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = if (allRequiredPermissionsGranted) {
            contentAlignment
        } else {
            Alignment.Center
        },
    ) {
        if (allRequiredPermissionsGranted) {
            onGranted(
                permissionState.permissions.filter { it.status.isGranted }.map { it.permission },
            )
        } else {
            PermissionScreen(
                permissionState,
                errorText = if (errorState) stringResource(id = R.string.location_request_denied_error) else "",
            )

        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun PermissionScreen(
    state: MultiplePermissionsState,
    errorText: String,
) {
    var showRationale by remember(state) {
        mutableStateOf(false)
    }

    fun onLocationEnableButtonClick() {
        if (state.shouldShowRationale) {
            showRationale = true
        } else {
            state.launchMultiplePermissionRequest()
        }
    }

    RequestLocationCTA(onButtonClick = { onLocationEnableButtonClick() }, errorText = errorText)

    if (showRationale) {
        RationaleDialog(
            onDismissRequest = {
                showRationale = false
            },
            onConfirm = {
                showRationale = false
                state.launchMultiplePermissionRequest()
            },
            onDismiss = {
                showRationale = false
            },
        )
    }
}


@Composable
private fun RequestLocationCTA(
    onButtonClick: () -> Unit,
    errorText: String,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.location_permission_denied),
            contentDescription = stringResource(id = R.string.location_request_icon_alt),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            contentScale = ContentScale.Fit,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.location_request_title),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = R.string.location_request_description),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            OutlinedButton(
                text = R.string.location_got_to_settings_button, action = {
                    context.startActivity(
                        Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                    )
                }, size = ButtonSize.LARGE
            )
            Spacer(modifier = Modifier.width(16.dp))
            FilledButton(
                text = R.string.location_request_button,
                action = onButtonClick,
                size = ButtonSize.LARGE
            )
        }

        if (errorText.isNotBlank()) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun RationaleDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(text = stringResource(id = R.string.location_rationale_title))
        },
        text = {
            Column {
                Text(
                    text = stringResource(id = R.string.location_rationale_description),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(id = R.string.location_rationale_privacy_note))

            }
        },
        confirmButton = {
            FilledButton(text = R.string.location_rationale_continue, action = onConfirm)
        },
        dismissButton = {
            OutlinedButton(text = R.string.location_rationale_dismiss, action = onDismiss)

        },
    )
}

@Composable
@Preview
fun Request() {
    RequestLocationCTA(onButtonClick = { }, errorText = "")
}

@Composable
@Preview
fun Rationale() {
    RationaleDialog(onDismissRequest = { }, onConfirm = { }, onDismiss = { })
}