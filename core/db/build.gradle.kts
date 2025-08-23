plugins {
    id("android.library.plugin")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.karanchuk.core.db"
}

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")

    implementation(project(":common:util"))
    implementation(project(":common:model"))

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}