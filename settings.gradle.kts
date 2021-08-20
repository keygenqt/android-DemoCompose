pluginManagement {

    apply("versions.gradle.kts")

    val gradleVersion: String by extra
    val kotlinVersion: String by extra
    val spotlessVersion: String by extra

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version gradleVersion
        id("com.android.library") version gradleVersion
        id("org.jetbrains.kotlin.android") version kotlinVersion
        id("com.diffplug.spotless") version spotlessVersion
        kotlin("kapt") version kotlinVersion
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "DemoContacts"
include(":app")
