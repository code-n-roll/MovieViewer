plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.karanchuk.repository.settings"
}

dependencies {
    implementation(project(":common:ui"))

    // for javax.inject.Inject
    implementation(libs.androidx.hilt.navigation.compose)

    // DataStore
    implementation(libs.androidx.dataStore)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}