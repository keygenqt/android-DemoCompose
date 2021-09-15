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
        kotlin("kapt") version kotlinVersion
        id("com.diffplug.spotless") version spotlessVersion
        id("com.jfrog.artifactory") version "4.24.16"
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://artifactory.keygenqt.com/artifactory/open-source")
    }
}
rootProject.name = "DemoContacts"
include(":app")

//include(":compose-forms")
//project(":compose-forms").projectDir = File(settingsDir, "../../Surf/libs/compose-forms")
