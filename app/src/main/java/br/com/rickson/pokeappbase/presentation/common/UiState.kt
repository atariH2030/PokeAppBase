package br.com.rickson.pokeappbase.presentation.common

sealed class UiState<out T> {
    data object Initial : UiState<Nothing>() // Tela abriu agora, aguardando você digitar
    data object Loading : UiState<Nothing>() // Bolinha girando
    data class Success<T>(val data: T) : UiState<T>() // Pokémon na tela!
    data class Error(val message: String) : UiState<Nothing>() // Deu ruim (ex: ID não existe)
}