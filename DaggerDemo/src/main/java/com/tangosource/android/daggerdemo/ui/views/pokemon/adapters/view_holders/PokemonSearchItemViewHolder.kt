package com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.view_holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tangosource.android.daggerdemo.model.PokemonSearchItem
import com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.PokemonItemClick
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonSearchItemViewHolder(root: View): RecyclerView.ViewHolder(root) {

    fun bind(index: Int, pokemonSearchItem: PokemonSearchItem, pokemonItemClick: PokemonItemClick) {
        itemView.name.text = pokemonSearchItem.name

        itemView.setOnClickListener { pokemonItemClick.invoke(index, pokemonSearchItem) }
    }
}