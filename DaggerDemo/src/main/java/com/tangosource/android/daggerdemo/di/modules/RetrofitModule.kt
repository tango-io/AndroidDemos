package com.tangosource.android.daggerdemo.di.modules

import com.tangosource.android.daggerdemo.utils.Base
import com.squareup.moshi.Moshi
import com.tangosource.android.daggerdemo.BuildConfig
import com.tangosource.android.daggerdemo.utils.Pokemon
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
class RetrofitModule {

    @Provides
    @Base
    fun retrofitBuilder(@Base okHttpClient: OkHttpClient, @Base moshi: Moshi): Retrofit.Builder {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
    }

    @Provides
    @Pokemon
    fun pokemonRetrofit(@Base builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl(BuildConfig.POKEMON_API_ROOT).build()
    }

}
