package br.com.rickson.pokeappbase.presentation.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rickson.pokeappbase.domain.common.Resource
import br.com.rickson.pokeappbase.domain.model.Pokemon
import br.com.rickson.pokeappbase.domain.usecase.GetPokemonUseCase
import br.com.rickson.pokeappbase.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonUseCase: GetPokemonUseCase
) : ViewModel() {

    // Guarda o estado atual da tela (começa no Initial)
    private val _uiState = MutableStateFlow<UiState<Pokemon>>(UiState.Initial)
    val uiState: StateFlow<UiState<Pokemon>> = _uiState.asStateFlow()

    // Função que será chamada quando o usuário clicar no botão "Buscar"
    fun searchPokemon(id: Int) {
        viewModelScope.launch {
            // Chama a Regra de Negócio (UseCase)
            getPokemonUseCase(id).collect { resource ->
                // Converte o Resource (do Domain) para o UiState (da Tela)
                when (resource) {
                    is Resource.Loading -> _uiState.value = UiState.Loading
                    is Resource.Success -> _uiState.value = UiState.Success(resource.data)
                    is Resource.Error -> _uiState.value = UiState.Error(resource.message)
                }
            }
        }
    }
}