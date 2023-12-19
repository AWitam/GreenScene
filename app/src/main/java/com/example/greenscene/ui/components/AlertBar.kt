package com.example.greenscene.ui.components


import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.greenscene.common.connectivity.ConnectivityObserver
import com.example.greenscene.common.connectivity.NetworkConnectivityObserver
import com.example.greenscene.common.connectivity.SystemBroadcastReceiver
import com.example.greenscene.common.connectivity.isAirplaneModeOn
import com.example.greenscene.R.string as AppText

@Composable
fun AlertBar(context: Context) {
    var isAirPlaneMode: Boolean by remember { mutableStateOf(isAirplaneModeOn(context)) }

    SystemBroadcastReceiver(Intent.ACTION_AIRPLANE_MODE_CHANGED) {
        val isAirplaneModeAfterChange = Settings.Global.getInt(
            context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0

        isAirPlaneMode = isAirplaneModeAfterChange
    }

    lateinit var connectivityObserver: ConnectivityObserver
    connectivityObserver = NetworkConnectivityObserver(context)

    val networkConnection by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Available
    )


    val noConnection =
        isAirPlaneMode || networkConnection == ConnectivityObserver.Status.Unavailable || networkConnection == ConnectivityObserver.Status.Lost


    if (noConnection) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(AppText.no_connection),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorScheme.error)
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = colorScheme.onError
            )
        }
    }
}