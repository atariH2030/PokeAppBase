# 🔄 Guia 03: Adaptando para Qualquer API ou Tema

Se o professor mudar o tema (ex: de Pokémon para Star Wars ou CEP), você não precisa recomeçar. Basta alterar estes 4 pontos específicos na ordem abaixo.

---

## 1. O Ponto de Partida: `di/networkModule.kt`
Altere a `baseUrl` para o endereço da nova API fornecida no enunciado ou no Swagger.

**Exemplo para Dragon Ball:**
```kotlin
// De: .baseUrl("[https://pokeapi.co/api/v2/](https://pokeapi.co/api/v2/)")
// Para:
.baseUrl("[https://dragon-ball-api-055bab372b94.herokuapp.com/api/](https://dragon-ball-api-055bab372b94.herokuapp.com/api/)")
```

## 2. O Espelho dos Dados: data/model/NovaResponse.kt

Observe o JSON que a API devolve (use o Swagger ou o navegador). Crie as classes que representam exatamente essas chaves.

Dica de Ouro: Se o JSON vier com nomes estranhos, use @SerialName para mapear para um nome melhor no Kotlin.

**Exemplo (API de Personagens):**
```kotlin
@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    @SerialName("image") val imageUrl: String // Mapeia 'image' do JSON para 'imageUrl'
)

// Não esqueça de atualizar a função de conversão para o Domain
fun CharacterResponse.toDomain() = Character(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl
)
```

## 3. A Rota da API: data/remote/NovaApi.kt

Ajuste os métodos @GET conforme os endpoints (caminhos) da nova API.

**Exemplo:**
```kotlin
interface CharacterApi {
    // Se a rota for: /characters/{id}
    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse
}
```


## 4. Ajustes Visuais: presentation/

Ajuste os métodos @GET conforme os endpoints (caminhos) da nova API.

**Exemplo:**

Se mudar o... | Altere o arquivo...  |  O que procurar?
- Endereço do Site | di/networkModule.kt | .baseUrl(...)
- Campos do JSON | data/model/...Response.kt | As propriedades da data class
- Caminho da Rota | data/remote/...Api.kt | "O texto dentro do @GET(""..."")"
- Nome das Telas | presentation/navigation/AppRoutes.kt | As strings das rotas



## ⚠️ Atenção com Listas vs Objetos
Algumas APIs devolvem uma Lista [] e outras um Objeto {}.
- Se o JSON começar com [, seu método na API deve retornar List<SuaResponse>.
- Se começar com {, retorna apenas SuaResponse.
