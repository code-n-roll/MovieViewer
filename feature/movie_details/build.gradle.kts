plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.karanchuk.feature.movie_details"
}

dependencies {
    implementation(project(":repository:favorite_movies"))
    implementation(project(":common:navigation"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.material)
    implementation(libs.androidx.compose.material.icons.extended)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.core)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}