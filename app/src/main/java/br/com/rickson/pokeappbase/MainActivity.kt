package br.com.rickson.pokeappbase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box // Importação do Box adicionada
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.rickson.pokeappbase.presentation.navigation.AppNavigation
import br.com.rickson.pokeappbase.ui.theme.PokeAppBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeAppBaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Chamamos a nossa tela aqui, aplicando o padding de segurança
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation() // Agora o app tem um sistema de rotas!
                    }
                }
            }
        }
    }
}