plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.karanchuk.common.util"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}