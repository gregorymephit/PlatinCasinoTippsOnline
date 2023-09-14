package com.resrplatin.ccasi.notipps.util

import com.resrplatin.ccasi.notipps.R

fun getBannerImageByTitle(title: String) = when(title) {
    "Hugo Legacy" -> R.drawable.hugo_legacy_i
    "Lightning Roulette" -> R.drawable.lighthning_roulette_i
    "Crazy Time" -> R.drawable.crazy_time_i
    "Football Studio" -> R.drawable.football_studio_i
    "Monopoly Live" -> R.drawable.monopoly_life_i
    "Crazy Coin Flip" -> R.drawable.crazy_coin_flip_i
    "XXXtreme Lightning" -> R.drawable.xxxtreme_lightning_i

    "Book Of Dead" -> R.drawable.book_of_dead_i
    "Book Of Demi Gods 4" -> R.drawable.book_of_demi_gods_i
    "Gates Of Olympus" -> R.drawable.gates_of_olympus_i
    "The Dog House" -> R.drawable.the_dog_house_i
    "Legacy Of Dead" -> R.drawable.legacy_of_dead_i
    "Sweet Bonanza" -> R.drawable.sweet_bonanza_i
    else -> throw IndexOutOfBoundsException()
}