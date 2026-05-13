package br.com.rickson.pokeappbase.di

import br.com.rickson.pokeappbase.data.remote.PokemonRemoteDataSource
import br.com.rickson.pokeappbase.data.remote.PokemonRemoteDataSourceImpl
import br.com.rickson.pokeappbase.data.repository.PokemonRepositoryImpl
import br.com.rickson.pokeappbase.domain.repository.PokemonRepository
import org.koin.dsl.module

val dataModule = module {
    // Quando alguém pedir a interface PokemonRemoteDataSource, entregue a implementação Impl
    // O get() manda o Koin buscar a PokemonApi que criamos no networkModule!
    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }

    // Quando alguém pedir a interface PokemonRepository, entregue a implementação Impl
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}