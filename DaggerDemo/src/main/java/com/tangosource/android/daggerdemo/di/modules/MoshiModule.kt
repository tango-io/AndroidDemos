package com.tangosource.android.daggerdemo.di.modules

import com.tangosource.android.daggerdemo.utils.Base
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class MoshiModule {
    @Provides
    @Base
    fun baseMoshi(): Moshi {
        return Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
    }
}