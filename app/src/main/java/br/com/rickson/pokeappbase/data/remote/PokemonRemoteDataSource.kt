package br.com.rickson.pokeappbase.data.remote

import br.com.rickson.pokeappbase.data.model.PokemonResponse

interface PokemonRemoteDataSource {
    suspend fun getPokemon(id: Int): PokemonResponse
}