# 📜 Guia 05: Implementando Listas e Detalhes Avançados

Use este guia se a prova pedir uma **Lista Inicial** (em vez de apenas uma busca) ou se os dados tiverem "listas dentro de listas" (como as transformações).

---

## 1. Mudança na Interface API (`CharacterApi.kt`)
Quando a API devolve vários itens, usamos `List<T>`.

```kotlin
interface CharacterApi {
    // Retorna uma lista [] de personagens para a tela inicial
    @GET("characters")
    suspend fun getCharacters(): List<CharacterResponse>

    // Retorna um único objeto {} para a tela de detalhes
    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterResponse
}
```

## 2. Mudança no Repositório (CharacterRepositoryImpl.kt)
Você precisará de dois métodos: um para a lista e outro para o detalhe.
``` kotlin
override suspend fun getCharacters(): Resource<List<Character>> {
    return try {
        val response = remoteDataSource.getCharacters()
        // Converte cada item da lista da API para o modelo do Domain
        Resource.Success(response.map { it.toDomain() })
    } catch (e: Exception) {
        Resource.Error("Erro ao carregar lista: ${e.message}")
    }
}
```

## 3. Desenhando a Lista Vertical (LazyColumn)
Na tela de listagem, usamos o LazyColumn (o "RecyclerView" do Compose).
``` kotlin
@Composable
fun CharacterListScreen(
    characters: List<Character>,
    onItemClick: (Int) -> Unit
) {
    LazyColumn {
        items(characters) { character ->
            // Um card ou linha para cada personagem
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onItemClick(character.id) }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = character.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp)
                    )
                    Text(text = character.name, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
```

## 4. Mostrando Listas Internas (Transformações)
Se o detalhe do personagem tiver transformações, use um LazyRow (lista horizontal) dentro da sua tela de detalhes.
``` kotlin
// Dentro da Column da DetailScreen
if (!character.transformations.isNullOrEmpty()) {
    Text("Transformações", style = MaterialTheme.typography.titleMedium)
    LazyRow {
        items(character.transformations) { trans ->
            Column(modifier = Modifier.padding(8.dp)) {
                AsyncImage(model = trans.image, modifier = Modifier.size(100.dp))
                Text(trans.name)
            }
        }
    }
}
```