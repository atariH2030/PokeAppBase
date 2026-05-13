# 🗺️ Guia 06: Mapa de Nomenclatura e Refatoração

Siga este mapa caso precise trocar o tema (ex: de Pokémon para Dragon Ball).
**DICA:** Use `Shift + F6` no nome da classe para renomear em todos os arquivos de uma vez.

---

## 1. Mudanças de Nomes de Arquivos (Caminhos)

| Camada | Nome Atual (Pokémon) | Novo Nome Sugerido (Dragon Ball) |
| :--- | :--- | :--- |
| **Data** | `data/model/PokemonResponse.kt` | `data/model/CharacterResponse.kt` |
| **Data** | `data/remote/PokemonApi.kt` | `data/remote/CharacterApi.kt` |
| **Data** | `data/remote/PokemonRemoteDataSource.kt` | `data/remote/CharacterRemoteDataSource.kt` |
| **Data** | `data/repository/PokemonRepositoryImpl.kt` | `data/repository/CharacterRepositoryImpl.kt` |
| **Domain** | `domain/model/Pokemon.kt` | `domain/model/Character.kt` |
| **Domain** | `domain/repository/PokemonRepository.kt` | `domain/repository/CharacterRepository.kt` |
| **Domain** | `domain/usecase/GetPokemonUseCase.kt` | `domain/usecase/GetCharacterUseCase.kt` |
| **Presentation** | `presentation/pokemon/PokemonViewModel.kt` | `presentation/character/CharacterViewModel.kt` |
| **Presentation** | `presentation/pokemon/PokemonScreen.kt` | `presentation/character/CharacterListScreen.kt` |

---

## 2. Mudanças dentro do Código (Variáveis e Classes)

Ao trocar os nomes, certifique-se de que a **lógica de tipo** acompanhe a mudança:

### A) Na ViewModel
* **De:** `_uiState = MutableStateFlow<UiState<Pokemon>>(UiState.Initial)`
* **Para:** `_uiState = MutableStateFlow<UiState<Character>>(UiState.Initial)`
* **De:** `fun searchPokemon(id: Int)`
* **Para:** `fun fetchCharacter(id: Int)`

### B) No UseCase
* **De:** `class GetPokemonUseCase(private val repository: PokemonRepository)`
* **Para:** `class GetCharacterUseCase(private val repository: CharacterRepository)`

### C) No Repository
* **De:** `suspend fun getPokemon(id: Int): Resource<Pokemon>`
* **Para:** `suspend fun getCharacter(id: Int): Resource<Character>`

---

## 3. Mudanças nos Módulos Koin (`di/`)

Aqui é onde você "liga os fios". Se você renomear as classes, precisa atualizar aqui:

### `presentationModule.kt`
```kotlin
// ANTES: viewModel { PokemonViewModel(get()) }
viewModel { CharacterViewModel(get()) }
```

### domainModule.kt
```
// ANTES: factory { GetPokemonUseCase(get()) }
factory { GetCharacterUseCase(get()) }
```

## 4. O "Pulo do Gato": O Objeto JSON
A mudança mais crítica é o mapeamento do JSON que vem da internet para o seu objeto.
1. Abra o CharacterResponse.kt.
2. Mude as variáveis para baterem com o JSON da nova API.
3. Use o @SerialName se o nome na API for diferente do que você quer usar no código.

Exemplo Prático (API DBZ):
```
@Serializable
data class CharacterResponse(
    val id: Int,
    val name: String,
    @SerialName("image") val imageUrl: String // Se no JSON for "image", mas você quer "imageUrl"
)
```

## 5. Resumo de Emergência
Se o tempo estiver acabando e você se perder:
1. Mude a Base URL no networkModule.
2. Mude as variáveis na Response para baterem com a nova API.
3. Aperte Build -> Clean Project.
4. O Android Studio vai te mostrar em vermelho todos os lugares onde o nome antigo ainda existe. Vá clicando e corrigindo.