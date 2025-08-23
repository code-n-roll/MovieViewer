import com.karanchuk.conventionplugins.base.extensions.androidConfig

plugins {
    id("android.base.config")
}


androidConfig {
    buildFeatures {
        compose = true
    }
}