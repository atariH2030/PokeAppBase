package br.com.rickson.pokeappbase.data.repository

import br.com.rickson.pokeappbase.data.model.toDomain
import br.com.rickson.pokeappbase.data.remote.PokemonRemoteDataSource
import br.com.rickson.pokeappbase.domain.common.Resource
import br.com.rickson.pokeappbase.domain.model.Pokemon
import br.com.rickson.pokeappbase.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val remoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {

    override suspend fun getPokemon(id: Int): Resource<Pokemon> {
        return try {
            // Tenta buscar da internet
            val response = remoteDataSource.getPokemon(id)
            // Se deu certo, converte pro modelo Domain e devolve Sucesso
            Resource.Success(response.toDomain())
        } catch (e: Exception) {
            // Se falhou (sem internet, ID não existe, etc), devolve Erro
            Resource.Error("Erro ao buscar o Pokémon: ${e.message}")
        }
    }
}