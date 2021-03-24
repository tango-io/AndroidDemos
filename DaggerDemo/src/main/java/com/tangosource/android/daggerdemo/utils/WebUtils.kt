package com.tangosource.android.daggerdemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.core.content.getSystemService
import okio.Buffer

fun Context.hasNetworkConnection(): Boolean {
    val connectivityManager = getSystemService<ConnectivityManager>()
    val nwInfo = connectivityManager?.activeNetworkInfo ?: return false
    return nwInfo.isConnected
}

fun Context.getConnectionType(): Int {
    val cm = getSystemService<ConnectivityManager>()
    val info = cm?.activeNetworkInfo
    return info?.type ?: -1
}

fun Context.isOnWifi(): Boolean {
    return getConnectionType() == ConnectivityManager.TYPE_WIFI
}