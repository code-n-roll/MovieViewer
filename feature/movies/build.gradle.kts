plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    id("android.base.config")
    id("android.base.test.config")
}

android {
    namespace = "com.karanchuk.feature.movies"
}

dependencies {

    implementation(project(":repository:movies"))
    implementation(project(":common:ui"))
    implementation(project(":common:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.material3)

    // Hilt
    implementation(libs.hilt.android.core)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    // Shimmer
    implementation(libs.composeShimmer)

    // for compose @Preview
    implementation(libs.androidx.ui.tooling)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.core)

    // Paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material.icons.extended)
}