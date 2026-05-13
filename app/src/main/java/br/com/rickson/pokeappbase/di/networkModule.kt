package br.com.rickson.pokeappbase.di

import br.com.rickson.pokeappbase.data.remote.PokemonApi
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    // single = Cria uma única instância (Singleton) para o app todo
    single {
        val contentType = "application/json".toMediaType()
        // Ignora chaves do JSON que não mapeamos no nosso PokemonResponse
        val json = Json { ignoreUnknownKeys = true }

        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/") // A URL base da PokeAPI
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    // Pede pro Retrofit criar a implementação da nossa interface PokemonApi
    single { get<Retrofit>().create(PokemonApi::class.java) }
}