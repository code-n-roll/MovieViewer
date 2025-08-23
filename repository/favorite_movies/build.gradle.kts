plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.karanchuk.repository.favorite_movies"
}

dependencies {

    implementation(project(":repository:movies"))
    implementation(project(":core:db"))
    implementation(project(":common:model"))
    implementation(project(":common:ui"))

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    // Hilt
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