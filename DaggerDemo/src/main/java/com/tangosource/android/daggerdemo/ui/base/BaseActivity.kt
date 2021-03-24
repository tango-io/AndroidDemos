package com.tangosource.android.daggerdemo.ui.base

import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.tangosource.android.daggerdemo.utils.showToast
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity(@LayoutRes layoutRes: Int): DaggerAppCompatActivity(layoutRes) {
    protected fun showToast(@StringRes messageRes: Int) = showToast(messageRes, Toast.LENGTH_LONG)

    protected fun showToast(message: String) = showToast(message, Toast.LENGTH_LONG)
}