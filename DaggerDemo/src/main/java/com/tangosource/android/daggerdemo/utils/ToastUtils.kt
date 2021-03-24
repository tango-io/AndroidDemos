package com.tangosource.android.daggerdemo.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT)
        = Toast.makeText(this, msg, duration).show()

fun Context.showToast(@StringRes msgResId: Int, duration: Int = Toast.LENGTH_SHORT)
        = showToast(this.getString(msgResId), duration)

fun Context.showCenterToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    val toast = Toast.makeText(this, msg, duration)
    toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0)
    toast.show()
}

fun Context.showCenterToast(@StringRes msgResId: Int, duration: Int = Toast.LENGTH_SHORT)
        = showCenterToast(this.getString(msgResId), duration)

fun Context.showSimpleDialog(@StringRes titleResId: Int, msg: String) {
    AlertDialog.Builder(this).apply {
        setTitle(titleResId)
        setMessage(msg)
        setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        show()
    }
}

fun Context.showSimpleDialog(@StringRes titleResId: Int, @StringRes msgResId: Int)
        = showSimpleDialog(titleResId, getString(msgResId))