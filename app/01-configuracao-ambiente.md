# 🛠️ Guia 01: Configuração de Ambiente e Dependências

Este guia foca em preparar o motor do app (Gradle) para aceitar as bibliotecas de arquitetura (Retrofit, Koin, Serialization).

## 1. O Arquivo Central: `gradle/libs.versions.toml`
Se o Android Studio da faculdade for moderno, este é o lugar onde você declara as versões. Se for uma versão muito antiga (Arctic Fox ou anterior), ignore este arquivo e coloque tudo direto no `build.gradle.kts (App)`.

### Bloco [versions]
```toml
# Versões estáveis (LTS) para evitar quebras
koin = "3.5.6"
retrofit = "2.11.0"
navigationCompose = "2.8.3"
coil = "2.7.0"
kotlinxSerialization = "1.6.3"
```

### Bloco [libraries]
```toml
# Koin (Injeção de Dependência)
koin-androidx-compose = { group = "io.insert-koin", name = "koin-androidx-compose", version.ref = "koin" }

# Retrofit & JSON (API)
retrofit = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-converter-kotlinx-serialization = { group = "com.squareup.retrofit2", name = "converter-kotlinx-serialization", version.ref = "retrofit" }
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerialization" }

# Navigation & Coil (Imagens)
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
coil-compose = { group = "io.coil-kt", name = "coil-compose", version.ref = "coil" }
```

### Bloco [plugins]
```toml
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
```

## 2. Configurando os Plugins (Kotlin DSL):

### Bloco [plugins]: `build.gradle.kts (Project)`
```toml
plugins {
    // ... plugins nativos do projeto
    alias(libs.plugins.kotlin.serialization) apply false
}
```

### Bloco [plugins]:`build.gradle.kts (Module: app)`
```toml
plugins {
    // ... plugins nativos do projeto
    alias(libs.plugins.kotlin.serialization) apply false
}
```

### Bloco [dependencies]:`build.gradle.kts (Module: app)`
```toml
dependencies {
// Importando as libs que declaramos no TOML
implementation(libs.koin.androidx.compose)
implementation(libs.retrofit)
implementation(libs.retrofit.converter.kotlinx.serialization)
implementation(libs.kotlinx.serialization.json)
implementation(libs.androidx.navigation.compose)
implementation(libs.coil.compose)
}
```


## 3. 🛡️ Blindagem contra Ambientes Antigos
Erro: "Unresolved reference" no import do Retrofit
Se a versão do Retrofit for anterior à 2.11, o conversor nativo não existirá.

Ação Corretiva:
1. No build.gradle.kts (App), substitua a linha do conversor por:
   implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
2. No código, use o import:
   import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory


## 4. Passo Final: Manifest e Application
AndroidManifest.xml

1. Permissão de Internet (antes da tag <application>):
   <uses-permission android:name="android.permission.INTERNET" />
2. Vincular a classe Application (dentro da tag <application>):
   android:name=".PokemonApplication"

### Alterar no `PokemonApplication.kt`
```toml
class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokemonApplication)
            modules(networkModule, dataModule, domainModule, presentationModule)
        }
    }
}
```