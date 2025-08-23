import com.karanchuk.conventionplugins.base.extensions.androidConfig
import com.karanchuk.conventionplugins.base.extensions.kotlinJvmCompilerOptions
import com.karanchuk.conventionplugins.base.extensions.libs
import com.karanchuk.conventionplugins.base.extensions.projectJavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

androidConfig {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

kotlinJvmCompilerOptions {
    jvmTarget.set(JvmTarget.fromTarget(projectJavaVersion.toString()))
}