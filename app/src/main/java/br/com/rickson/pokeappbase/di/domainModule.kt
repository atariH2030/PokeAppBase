package br.com.rickson.pokeappbase.di

import br.com.rickson.pokeappbase.domain.usecase.GetPokemonUseCase
import org.koin.dsl.module

val domainModule = module {
    // factory = Cria uma nova instância toda vez que for chamado
    factory { GetPokemonUseCase(get()) }
}