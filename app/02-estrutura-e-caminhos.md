# 📂 Guia 02: Estrutura de Pastas e Arquivos (Clean Architecture)

Siga esta ordem de criação para garantir que os pacotes existam antes de criar os arquivos.

## 1. Mapeamento de Pastas (Packages)
Caminho base: `app/src/main/java/br/com/seu_projeto/`

* 📂 `data/`
    * `model/` (DTOs/Responses da API)
    * `remote/` (Interfaces Retrofit e DataSources)
    * `repository/` (Implementações dos Repositórios)
* 📂 `domain/`
    * `common/` (Resource.kt)
    * `model/` (Classes de dados puras/Entities)
    * `repository/` (Interfaces dos Repositórios)
    * `usecase/` (Regras de negócio)
* 📂 `presentation/`
    * `common/` (UiState.kt)
    * `navigation/` (Rotas e Grafo)
    * `pokemon/` (Telas e ViewModels)
* 📂 `di/` (Módulos do Koin)

---

## 2. Checklist de Arquivos e Responsabilidades

### Camada Domain (A primeira a ser feita)
1.  `Resource.kt` (`domain/common`): Mensageiro de Sucesso/Erro/Loading.
2.  `Pokemon.kt` (`domain/model`): Como o dado deve ser no app (limpo).
3.  `PokemonRepository.kt` (`domain/repository`): Interface que define o que o app faz.
4.  `GetPokemonUseCase.kt` (`domain/usecase`): A lógica (ex: não aceitar ID < 1).

### Camada Data (Onde a internet acontece)
1.  `PokemonResponse.kt` (`data/model`): Espelho do JSON da API + função `.toDomain()`.
2.  `PokemonApi.kt` (`data/remote`): Interface do Retrofit com os `@GET`.
3.  `PokemonRemoteDataSource.kt` (`data/remote`): Interface e Impl para buscar dados.
4.  `PokemonRepositoryImpl.kt` (`data/repository`): Conecta o DataSource e converte pra Domain.

### Camada Presentation (O que o usuário vê)
1.  `UiState.kt` (`presentation/common`): Estado da tela (Initial, Loading, Success, Error).
2.  `PokemonViewModel.kt` (`presentation/pokemon`): Gerencia o estado e chama o UseCase.
3.  `PokemonScreen.kt` (`presentation/pokemon`): A interface em Compose.
4.  `AppRoutes.kt` e `AppNavigation.kt` (`presentation/navigation`): O mapa de telas.

---

## 💡 Dica de Velocidade no Android Studio
Ao criar um arquivo que depende de outro (ex: ViewModel que pede UseCase), o nome vai ficar vermelho.
* **Ação:** Clique no nome vermelho e aperte `Alt + Enter`.
* **Resultado:** O Android Studio fará o `import` automático. Se ele não sugerir o import, é porque você esqueceu de criar o arquivo ou errou o nome do pacote.