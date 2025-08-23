import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.karanchuk.conventionplugins.project"

dependencies {
    implementation(libs.gradleplugin.android)
    implementation(libs.gradleplugin.kotlin)
    // Workaround for version catalog working inside precompiled scripts
    // Issue - https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.gradleplugin.base)
}

private val projectJavaVersion: JavaVersion = JavaVersion.toVersion(libs.versions.javaConvention.get())

java {
    sourceCompatibility = projectJavaVersion
    targetCompatibility = projectJavaVersion
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}

gradlePlugin {
    plugins {
        register("android.application.plugin") {
            id = "android.application.plugin"
            implementationClass =
                "com.karanchuk.conventionplugins.project.AndroidApplicationPlugin"
        }

        register("android.library.plugin") {
            id = "android.library.plugin"
            implementationClass =
                "com.karanchuk.conventionplugins.project.AndroidLibraryPlugin"
        }
    }
}