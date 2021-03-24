package com.tangosource.android.daggerdemo.di.modules

import dagger.Module
import dagger.android.AndroidInjector
import dagger.internal.Beta
import dagger.multibindings.Multibinds


/**
 * Contains bindings to ensure the usability of {@code dagger.android} framework classes. This
 * module should be installed in the component that is used to inject the {@link
 * android.app.Application} class.
 */
@Beta
@Module
abstract class AndroidInjectionModule private constructor() {
    @Multibinds
    abstract fun classKeyedInjectorFactories(): Map<Class<Any>?, AndroidInjector.Factory<Any>>
    @Multibinds
    abstract fun stringKeyedInjectorFactories(): Map<String?, AndroidInjector.Factory<*>>
}