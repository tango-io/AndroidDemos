package com.tangosource.android.daggerdemo.utils

import javax.inject.Qualifier
import javax.inject.Scope


@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Scope
annotation class AppSingleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Base

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Pokemon

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MockAPI

@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.FIELD)
annotation class SharedPrefFileName
