package br.com.rickson.pokeappbase.presentation.navigation

sealed class AppRoutes(val route: String) {
    data object PokemonSearch : AppRoutes("pokemon_search")
    data object PokemonDetail : AppRoutes("pokemon_detail/{pokemonId}") {
        // Função auxiliar para criar a rota com o ID real
        fun createRoute(id: Int) = "pokemon_detail/$id"
    }
}