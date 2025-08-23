import java.util.Properties

plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.karanchuk.common.model"

    defaultConfig {
        buildConfigField("String", "API_KEY", "\"${getApiKey("API_KEY")}\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

// extension on Project to read a property from local.properties
fun Project.getApiKey(key: String): String =
    Properties().run {
        load(rootProject.file("local.properties").inputStream())
        getProperty(key)
    }

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")

    implementation(libs.retrofit.converter.kotlinx.serialization)

    // for @Qualifier annotations only
    implementation(libs.hilt.android.core)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}