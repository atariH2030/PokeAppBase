package br.com.rickson.pokeappbase.data.model

import br.com.rickson.pokeappbase.domain.model.Pokemon
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val id: Int,
    val name: String,
    val sprites: Sprites
)

@Serializable
data class Sprites(
    val other: OtherSprites
)

@Serializable
data class OtherSprites(
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork
)

@Serializable
data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String? = null
)

// FUNÇÃO MÁGICA: Converte essa salada de JSON para o nosso Modelo Limpo (Domain)
fun PokemonResponse.toDomain(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name.replaceFirstChar { it.uppercase() }, // Deixa a primeira letra maiúscula
        imageUrl = this.sprites.other.officialArtwork.frontDefault ?: ""
    )
}