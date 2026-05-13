plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Adicione esta linha:
    alias(libs.plugins.kotlin.serialization) apply false
}