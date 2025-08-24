plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.karanchuk.feature.favorite_movies"
}

dependencies {

    implementation(project(":repository:favorite_movies"))
    implementation(project(":common:model"))
    implementation(project(":common:ui"))

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    testImplementation(libs.junit)
    
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android.core)
    ksp(libs.hilt.compiler)

    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.material)
}