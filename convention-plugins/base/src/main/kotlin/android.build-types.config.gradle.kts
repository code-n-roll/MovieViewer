import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug") // dev only, for quick release signing
            isShrinkResources = true
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}