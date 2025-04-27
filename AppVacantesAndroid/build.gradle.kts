plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // Añade estos plugins
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.navigation.safe.args) apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.0" apply false
}