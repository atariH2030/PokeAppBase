package br.com.rickson.pokeappbase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.rickson.pokeappbase.presentation.pokemon.PokemonScreen
import br.com.rickson.pokeappbase.presentation.pokemon.detail.PokemonDetailScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.PokemonSearch.route
    ) {
        // TELA 1: BUSCA
        composable(AppRoutes.PokemonSearch.route) {
            PokemonScreen(
                onNavigateToDetail = { pokemonId ->
                    // Chama a rota dinâmica, injetando o número do Pokémon
                    navController.navigate(AppRoutes.PokemonDetail.createRoute(pokemonId))
                }
            )
        }

        // TELA 2: DETALHE
        composable(
            route = AppRoutes.PokemonDetail.route,
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Extrai o ID da rota
            val id = backStackEntry.arguments?.getInt("pokemonId") ?: 0

            PokemonDetailScreen(
                pokemonId = id,
                onBackClick = {
                    // Volta para a tela anterior
                    navController.popBackStack()
                }
            )
        }
    }
}