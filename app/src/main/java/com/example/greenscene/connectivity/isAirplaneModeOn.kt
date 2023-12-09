package com.example.greenscene.connectivity

import android.content.Context
import android.provider.Settings

fun isAirplaneModeOn(context: Context): Boolean {
    return Settings.Global.getInt(
        context.contentResolver,
        Settings.Global.AIRPLANE_MODE_ON, 0
    ) != 0
}