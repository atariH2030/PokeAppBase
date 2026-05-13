package br.com.rickson.pokeappbase.presentation.pokemon

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.rickson.pokeappbase.presentation.common.UiState
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonScreen(
    // O Koin cria e entrega a ViewModel pronta aqui!
    viewModel: PokemonViewModel = koinViewModel()
) {
    // Fica escutando as mudanças de estado da ViewModel
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Guarda o que o usuário digita no campo de texto
    var pokemonId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de texto
        OutlinedTextField(
            value = pokemonId,
            onValueChange = { pokemonId = it },
            label = { Text("Número do Pokémon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de busca
        Button(
            onClick = {
                val id = pokemonId.toIntOrNull()
                if (id != null) {
                    viewModel.searchPokemon(id)
                }
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // A MÁGICA ACONTECE AQUI: A tela muda dependendo do estado
        when (val state = uiState) {
            is UiState.Initial -> {
                Text("Digite o número (ex: 25 para Pikachu)")
            }
            is UiState.Loading -> {
                CircularProgressIndicator() // Bolinha de carregamento
            }
            is UiState.Success -> {
                // Desenha o Pokémon!
                Text(
                    text = state.data.name,
                    style = MaterialTheme.typography.headlineLarge
                )
                AsyncImage(
                    model = state.data.imageUrl,
                    contentDescription = "Imagem do ${state.data.name}",
                    modifier = Modifier.size(250.dp)
                )
            }
            is UiState.Error -> {
                Text(
                    text = state.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}