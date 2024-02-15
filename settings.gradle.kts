pluginManagement {
    repositories {
        google()
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

rootProject.name = "Online Store"
include(":app")
include(":feature_registration")
include(":feature_home")
include(":feature_catalog")
include(":core")
include(":setting_provider")
include(":feature_bag")
include(":feature_discount")
include(":feature_account")
include(":remote")
