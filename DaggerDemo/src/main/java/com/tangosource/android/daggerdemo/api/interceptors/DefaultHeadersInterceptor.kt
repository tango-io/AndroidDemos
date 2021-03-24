package com.tangosource.android.daggerdemo.api.interceptors

import androidx.core.os.LocaleListCompat
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class DefaultHeadersInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val sortedLanguages = LocaleListCompat.getAdjustedDefault().toLanguageTags()
        val modifiedRequest = originalRequest.newBuilder()
                .header("Accept-Language", sortedLanguages)
                .build()
        return chain.proceed(modifiedRequest)
    }
}