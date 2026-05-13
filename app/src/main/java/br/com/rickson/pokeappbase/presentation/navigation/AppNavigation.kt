package br.com.rickson.pokeappbase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.rickson.pokeappbase.presentation.pokemon.PokemonScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.PokemonSearch.route
    ) {
        // TELA 1: BUSCA
        composable(AppRoutes.PokemonSearch.route) {
            PokemonScreen()
            // Dica: Futuramente você passará o navController aqui para
            // navegar quando clicar em 'Buscar'.
        }

        // TELA 2: DETALHE (Exemplo de como seria)
        composable(
            route = AppRoutes.PokemonDetail.route,
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("pokemonId")
            // Aqui você chamaria a PokemonDetailScreen(id)
        }
    }
}