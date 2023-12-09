package com.example.greenscene.ui.components

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.greenscene.utils.SystemBroadcastReceiver
import com.example.greenscene.utils.isAirplaneModeOn

@Composable
fun AlertBar (context: Context) {
    var isAirPlaneMode : Boolean by remember{ mutableStateOf(isAirplaneModeOn(context)) }

    SystemBroadcastReceiver(Intent.ACTION_AIRPLANE_MODE_CHANGED) {
        val isAirplaneModeAfterChange =  Settings.Global.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0

        isAirPlaneMode = isAirplaneModeAfterChange
    }


    if(isAirPlaneMode) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "No Internet Connection",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.error)
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onError
            )
        }
    }
}