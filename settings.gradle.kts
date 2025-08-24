pluginManagement {
    includeBuild("convention-plugins/base")
    includeBuild("convention-plugins/project")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MovieViewer"

include(":app")
include(":common:model")
include(":common:navigation")
include(":common:ui")
include(":common:util")
include(":core:db")
include(":feature:favorite_movies")
include(":feature:movies")
include(":feature:movie_details")
include(":repository:favorite_movies")
include(":repository:movies")