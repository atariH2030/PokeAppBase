package br.com.rickson.pokeappbase.data.remote

import br.com.rickson.pokeappbase.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    // Essa é a rota da PokeAPI para buscar por ID
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonResponse
}