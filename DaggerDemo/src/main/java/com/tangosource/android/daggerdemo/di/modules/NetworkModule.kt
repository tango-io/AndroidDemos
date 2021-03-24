package com.tangosource.android.daggerdemo.di.modules

import com.tangosource.android.daggerdemo.BuildConfig
import com.tangosource.android.daggerdemo.api.interceptors.DefaultHeadersInterceptor
import com.tangosource.android.daggerdemo.api.interceptors.DefaultQueryParamsInterceptor
import com.tangosource.android.daggerdemo.di.modules.MoshiModule
import com.tangosource.android.daggerdemo.di.modules.RetrofitModule
import com.tangosource.android.daggerdemo.utils.AppSingleton
import com.tangosource.android.daggerdemo.utils.Base
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


@Module(includes = [MoshiModule::class, RetrofitModule::class])
class NetworkModule {

    @Provides
    @AppSingleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cache(null)
                .addInterceptor(DefaultHeadersInterceptor())
                .build()
    }

    @Provides
    @AppSingleton
    @Base
    fun withQueryParameters(
            okHttpClient: OkHttpClient,
            defaultQueryParamsInterceptor: DefaultQueryParamsInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        } else {
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return  okHttpClient.newBuilder()
                .addInterceptor(defaultQueryParamsInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }
}
