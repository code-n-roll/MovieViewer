package com.karanchuk.conventionplugins.project

import com.android.build.api.dsl.ApplicationDefaultConfig
import com.karanchuk.conventionplugins.base.extensions.androidConfig
import com.karanchuk.conventionplugins.base.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.android.application.get().pluginId)
                apply("android.base.config")
                apply("android.base.test.config")
            }

            androidConfig {
                defaultConfig {
                    this as ApplicationDefaultConfig

                    targetSdk = libs.versions.targetSdk.get().toInt()

                    versionCode = libs.versions.appVersionCode.get().toInt()
                    versionName = libs.versions.appVersionName.get()
                }
            }
        }
    }
}