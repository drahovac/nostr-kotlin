pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://kotlin.bintray.com/kotlinx")
    }
}

rootProject.name = "KtMultiNostr"
include(":androidApp")
include(":shared")