package com.tangosource.android.daggerdemo.di

import android.content.Context
import com.tangosource.android.daggerdemo.DaggerDemoApplication
import com.tangosource.android.daggerdemo.di.modules.*
import com.tangosource.android.daggerdemo.utils.AppSingleton
import dagger.Component
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        ApplicationFeatureModule::class,
        NetworkModule::class
    ]
)
@AppSingleton
interface AppComponent : AndroidInjector<DaggerDemoApplication> {
    fun context(): Context
}