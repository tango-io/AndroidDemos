package com.tangosource.android.daggerdemo.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule internal constructor(private val application: Application) {
    @Provides
    fun provideContext(): Context = application
}
