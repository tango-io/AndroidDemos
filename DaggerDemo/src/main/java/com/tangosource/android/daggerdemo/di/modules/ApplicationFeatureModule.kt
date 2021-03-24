package com.tangosource.android.daggerdemo.di.modules

import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonSearchActivity
import com.tangosource.android.daggerdemo.ui.views.pokemon.fragments.PokemonInfoBottomSheetDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ApplicationFeatureModule {

    @ContributesAndroidInjector(modules = [PokemonModule::class])
    fun contributePokemonSearchActivity(): PokemonSearchActivity

    @ContributesAndroidInjector(modules = [PokemonModule::class])
    fun contributePokemonInfoBottomSheetDialogFragment(): PokemonInfoBottomSheetDialogFragment
}
