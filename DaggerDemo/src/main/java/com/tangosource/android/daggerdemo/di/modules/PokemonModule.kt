package com.tangosource.android.daggerdemo.di.modules

import androidx.lifecycle.ViewModel
import com.tangosource.android.daggerdemo.ui.views.pokemon.view_model.PokemonInfoViewModel
import com.tangosource.android.daggerdemo.ui.views.pokemon.view_model.PokemonSearchViewModel
import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonRepository
import com.tangosource.android.daggerdemo.api.PokemonAPI
import com.tangosource.android.daggerdemo.api.mocks.MockPokemonAPI
import com.tangosource.android.daggerdemo.utils.MockAPI
import com.tangosource.android.daggerdemo.utils.Pokemon
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create

@Module
interface PokemonModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonSearchViewModel::class)
    fun pokemonSearchViewModel(viewModel: PokemonSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonInfoViewModel::class)
    fun pokemonInfoViewModel(viewModel: PokemonInfoViewModel): ViewModel

    companion object {
        @Provides
        fun providePokemonAPI(@Pokemon retrofit: Retrofit): PokemonAPI = retrofit.create()

        @Provides
        @MockAPI
        fun provideMockPokemonAPI(): PokemonAPI = MockPokemonAPI()

        @Provides
        fun providePokemonRepository(pokemonAPI: PokemonAPI) = PokemonRepository(pokemonAPI)
    }
}