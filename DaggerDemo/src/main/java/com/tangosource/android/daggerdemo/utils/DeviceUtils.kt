package com.tangosource.android.daggerdemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Point
import android.provider.Settings
import android.view.WindowManager
import java.util.*

private const val DEVICE_ID = "device_id"
@SharedPrefFileName
private const val PREF_NAME = "pref"

fun Context.sharedPreferences(name: String = PREF_NAME, mode: Int = Context.MODE_PRIVATE): SharedPreferences = getSharedPreferences(name, mode)

fun Context.getWidth(): Int {
    return getSize().x
}

fun Context.getHeight(): Int {
    return getSize().y
}

fun Context.getSize(): Point {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}

/**
 * Return a unique device id Use Secure.ANDROID_ID if available. If null or returns a common value, generate our
 * own.
 */
@SuppressLint("HardwareIds")
fun Context.getDeviceId(): String {
    var deviceId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    if (deviceId == null || "9774d56d682e549c" == deviceId) {
        deviceId = getPseudoDeviceId()
    }
    return deviceId
}

@SuppressLint("ApplySharedPref")
private fun Context.getPseudoDeviceId(): String {
    var deviceId = sharedPreferences().getString(DEVICE_ID, null)
    if (deviceId == null) {
        deviceId = UUID.randomUUID().toString()
        sharedPreferences().edit().putString(DEVICE_ID, deviceId).commit()
    }
    return deviceId
}
