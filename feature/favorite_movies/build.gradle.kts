plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.karanchuk.feature.favorite_movies"
}

dependencies {

    implementation(project(":repository:favorite_movies"))
    implementation(project(":common:model"))

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