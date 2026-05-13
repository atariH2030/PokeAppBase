package br.com.rickson.pokeappbase.di

import br.com.rickson.pokeappbase.presentation.pokemon.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    // Instancia o nosso PokemonViewModel injetando o GetPokemonUseCase
    viewModel { PokemonViewModel(get()) }
}