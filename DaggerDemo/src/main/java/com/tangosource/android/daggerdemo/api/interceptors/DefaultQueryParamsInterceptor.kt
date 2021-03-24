package com.tangosource.android.daggerdemo.api.interceptors

import android.content.Context
import com.tangosource.android.daggerdemo.const.*
import com.tangosource.android.daggerdemo.utils.getDeviceId
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultQueryParamsInterceptor @Inject constructor(context: Context): Interceptor {

    private val defaultParams = mapOf(
            FORMAT to FORMAT_JSON,
            DEVICE_ID to context.getDeviceId()
    )


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder().apply {
            defaultParams.forEach { setQueryParameter(it.key, it.value) }
        }.build()

        val modifiedRequest = originalRequest.newBuilder().url(newUrl).build()
        return chain.proceed(modifiedRequest)
    }
}
