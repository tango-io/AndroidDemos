package com.tangosource.android.daggerdemo.enums

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import com.tangosource.android.daggerdemo.R

enum class PokemonTypes(@StringRes val nameId: Int, @ColorInt val colorId: Int) {
    NORMAL(R.string.normal, R.color.normal),
    FIRE(R.string.fire, R.color.fire),
    FIGHTING(R.string.fighting, R.color.fighting),
    WATER(R.string.water, R.color.water),
    FLYING(R.string.flying, R.color.flying),
    GRASS(R.string.grass, R.color.grass),
    POISON(R.string.poison, R.color.poison),
    ELECTRIC(R.string.electric, R.color.electric),
    GROUND(R.string.ground, R.color.ground),
    PSYCHIC(R.string.psychic, R.color.psychic),
    ROCK(R.string.rock, R.color.rock),
    ICE(R.string.ice, R.color.ice),
    BUG(R.string.bug, R.color.bug),
    DRAGON(R.string.dragon, R.color.dragon),
    GHOST(R.string.ghost, R.color.ghost),
    DARK(R.string.dark, R.color.dark),
    STEEL(R.string.steel, R.color.steel),
    FAIRY(R.string.fairy, R.color.fairy),
    UNKNOWN(R.string.unknown, R.color.unknown);

    companion object  {
        fun getPokemonTypeByName(context: Context, name: String) =
            values().find { context.getString(it.nameId).equals(name, true) } ?: UNKNOWN
    }
}