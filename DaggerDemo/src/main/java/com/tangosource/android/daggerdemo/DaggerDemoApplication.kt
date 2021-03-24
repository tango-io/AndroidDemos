package com.tangosource.android.daggerdemo

import android.app.Application
import com.tangosource.android.daggerdemo.di.AppComponent
import com.tangosource.android.daggerdemo.di.DaggerAppComponent
import com.tangosource.android.daggerdemo.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DaggerDemoApplication : Application(), HasAndroidInjector {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}