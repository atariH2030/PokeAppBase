package br.com.rickson.pokeappbase.domain.usecase

import br.com.rickson.pokeappbase.domain.common.Resource
import br.com.rickson.pokeappbase.domain.model.Pokemon
import br.com.rickson.pokeappbase.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonUseCase(
    private val repository: PokemonRepository
) {
    // Usamos o operador 'invoke' para que possamos chamar a classe como se fosse uma função
    operator fun invoke(id: Int): Flow<Resource<Pokemon>> = flow {
        emit(Resource.Loading) // Primeiro, avisa que está carregando

        // Verifica se o id é válido (Regra de Negócio!)
        if (id <= 0) {
            emit(Resource.Error("Número inválido! O ID deve ser maior que zero."))
            return@flow
        }

        // Tenta buscar o Pokémon
        val result = repository.getPokemon(id)
        emit(result) // Entrega o Sucesso ou o Erro que veio lá da API
    }
}