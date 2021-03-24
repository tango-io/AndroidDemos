package com.tangosource.android.daggerdemo.utils

import com.squareup.moshi.Moshi

inline fun <reified T> Moshi.toJson(obj: T): String {
    return adapter(T::class.java).toJson(obj)
}

inline fun <reified T> Moshi.fromJson(string: String): T? {
    return adapter(T::class.java).fromJson(string)
}