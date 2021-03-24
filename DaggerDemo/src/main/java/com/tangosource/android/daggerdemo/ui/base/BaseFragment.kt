package com.tangosource.android.daggerdemo.ui.base

import android.widget.Toast
import androidx.annotation.StringRes
import com.tangosource.android.daggerdemo.utils.showToast
import dagger.android.support.DaggerFragment

open class BaseFragment(layoutRes: Int) : DaggerFragment(layoutRes) {
    protected fun showToast(@StringRes messageRes: Int) = context?.showToast(messageRes, Toast.LENGTH_LONG)

    protected fun showToast(message: String) = context?.showToast(message, Toast.LENGTH_LONG)
}
