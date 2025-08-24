plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.karanchuk.common.navigation"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}