package com.karanchuk.conventionplugins.project

import com.karanchuk.conventionplugins.base.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.plugins.android.library.get().pluginId)
                apply("android.base.config")
                apply("android.base.test.config")
            }
        }
    }
}