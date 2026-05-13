package br.com.rickson.pokeappbase.domain.repository

import br.com.rickson.pokeappbase.domain.common.Resource
import br.com.rickson.pokeappbase.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemon(id: Int): Resource<Pokemon>
}