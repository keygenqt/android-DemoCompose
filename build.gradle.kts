buildscript {

    apply("versions.gradle.kts")

    val kotlinVersion: String by extra
    val gradleVersion: String by extra
    val hiltCoreVersion: String by extra
    val googleServicesVersion: String by extra
    val crashlyticsPluginVersion: String by extra

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltCoreVersion")
        classpath("com.google.gms:google-services:$googleServicesVersion")
        classpath("com.google.firebase:firebase-crashlytics-gradle:$crashlyticsPluginVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}