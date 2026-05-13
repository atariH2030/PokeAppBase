package br.com.rickson.pokeappbase.data.remote

import br.com.rickson.pokeappbase.data.model.PokemonResponse

class PokemonRemoteDataSourceImpl(
    private val api: PokemonApi
) : PokemonRemoteDataSource {

    override suspend fun getPokemon(id: Int): PokemonResponse {
        return api.getPokemon(id)
    }
}