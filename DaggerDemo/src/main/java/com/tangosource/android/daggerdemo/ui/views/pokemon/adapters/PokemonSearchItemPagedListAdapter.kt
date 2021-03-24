package com.tangosource.android.daggerdemo.ui.views.pokemon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.tangosource.android.daggerdemo.R
import com.tangosource.android.daggerdemo.model.PokemonSearchItem
import com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.view_holders.PokemonSearchItemViewHolder

typealias PokemonItemClick = (Int, PokemonSearchItem) -> Unit

class PokemonSearchItemPagedListAdapter(private val pokemonItemClick: PokemonItemClick):
    PagedListAdapter<PokemonSearchItem, PokemonSearchItemViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonSearchItemViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent,false)
        return PokemonSearchItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonSearchItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(position, it, pokemonItemClick) }
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<PokemonSearchItem>() {
    override fun areItemsTheSame(oldItem: PokemonSearchItem, newItem: PokemonSearchItem) = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: PokemonSearchItem, newItem: PokemonSearchItem): Boolean {
        return oldItem.name == newItem.name
                && oldItem.url == newItem.url
    }
}