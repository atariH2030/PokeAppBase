# ⚡ Guia 04: Atalhos e Dicas de Produtividade (Modo Prova)

Este guia contém os comandos que vão te economizar tempo precioso e resolver erros bobos de digitação ou importação.

---

## 1. A "Trindade Sagrada" de Atalhos
Decore estes três, eles resolvem 90% dos problemas:

1.  **Alt + Enter**: O "Conserta Tudo".
    * Ficou vermelho? Clica em cima e aperta isso. Ele importa classes e sugere correções automáticas.
2.  **Duplo Shift (Search Everywhere)**:
    * Perdeu onde está o arquivo `PokemonRepositoryImpl`? Aperte Shift duas vezes e digite o nome. Ele te leva direto para lá.
3.  **Ctrl + Espaço**: Autocompletar Forçado.
    * Não lembra se a função é `koinViewModel()` ou `getViewModel()`? Digite as primeiras letras e use isso para ver o que a IDE sugere.

---

## 2. Resolvendo Erros "Fantasmas" (Gerais)
Às vezes o código está certo, mas o Android Studio continua mostrando erro.

* **Menu superior: Build -> Clean Project**: Limpa a sujeira da compilação anterior.
* **Menu superior: Build -> Rebuild Project**: Força o Gradle a ler tudo do zero.
* **Botão do Elefante (Sync Now)**: Sempre que mexer no `build.gradle` ou no `libs.versions.toml`, clique nele. Sem o Sync, o Android Studio não reconhece as bibliotecas novas.

---

## 3. Checklist de "Check-in" (Antes de entregar a prova)
Bata o olho nestes itens antes de dizer "Terminei":

- [ ] **Internet:** A permissão `<uses-permission...>` está no `AndroidManifest`?
- [ ] **Application:** O `android:name=".PokemonApplication"` está na tag `<application>` do Manifest?
- [ ] **Koin:** Todos os módulos (`network`, `data`, `domain`, `presentation`) foram adicionados na lista `modules(...)` do arquivo Application?
- [ ] **toDomain:** A sua classe de resposta da API tem a função de conversão para o modelo do Domain?
- [ ] **UiState:** A sua tela trata os 4 estados (`Initial`, `Loading`, `Success`, `Error`)?

---

## 4. Dica Extra: Copiar e Colar Inteligente
Se o professor pedir para trocar o tema, use o **Refactor**:
1. Clique com o botão direito no nome de uma classe (ex: `Pokemon`).
2. Vá em **Refactor -> Rename** (ou use **Shift + F6**).
3. Digite o novo nome (ex: `Character`).
4. O Android Studio vai trocar o nome em **todos os arquivos do projeto** ao mesmo tempo.