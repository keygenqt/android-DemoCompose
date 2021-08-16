apply("../versions.gradle.kts")

val ex = project.extra

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")

    // https://dagger.dev/hilt/
    id("dagger.hilt.android.plugin")
    // https://github.com/Kotlin/kotlinx.serialization
    kotlin("plugin.serialization")
    // https://github.com/diffplug/spotless
    id("com.diffplug.spotless")
    // https://developers.google.com/android/guides/google-services-plugin
    id("com.google.gms.google-services")
    // https://firebase.google.com/products/crashlytics
    id("com.google.firebase.crashlytics")
}

spotless {
    kotlin {
        target("**/*.kt")
        licenseHeaderFile("${project.projectDir}/../spotless.license")
    }
    kotlinGradle {
        target("*.gradle.kts")
        ktlint()
    }
}

android {

    compileSdk = 31

    defaultConfig {

        // secret token
        buildConfigField("String", "AUTHORIZATION_TOKEN", findProperty("rivgosh_token").toString())

        applicationId = "com.keygenqt.demo_contacts"

        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = project.property("composeVersion") as String
    }
    // division resources
    sourceSets {
        getByName("main").let { data ->
            data.res.setSrcDirs(emptySet<String>())
            file("src/main/res").listFiles()?.toList()?.forEach { dir ->
                data.res.srcDir(dir)
            }
        }
    }
}

dependencies {

    // base
    implementation("androidx.core:core-ktx:${ex["coreKtxVersion"]}")

    // retrofit
    implementation("com.squareup.retrofit2:converter-gson:${ex["retrofitVersion"]}")
    implementation("com.squareup.retrofit2:retrofit:${ex["retrofitVersion"]}")
    implementation("com.squareup.okhttp3:logging-interceptor:${ex["loggingInterceptor"]}")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:${ex["firebaseBomVersion"]}"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")

    // room
    implementation("androidx.room:room-runtime:${ex["roomVersion"]}")
    implementation("androidx.room:room-ktx:${ex["roomVersion"]}")
    kapt("androidx.room:room-compiler:${ex["roomVersion"]}")

    // hilt
    implementation("com.google.dagger:hilt-android:${ex["hiltCoreVersion"]}")
    kapt("com.google.dagger:hilt-compiler:${ex["hiltCoreVersion"]}")
    kapt("androidx.hilt:hilt-compiler:${ex["hiltVersion"]}")
    implementation("androidx.hilt:hilt-navigation-compose:${ex["hiltComposeNavigationVersion"]}")

    // accompanist
    implementation("com.google.accompanist:accompanist-insets:${ex["accompanistVersion"]}")
    implementation("com.google.accompanist:accompanist-pager:${ex["accompanistVersion"]}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${ex["accompanistVersion"]}")

    // compose
    implementation("androidx.compose.ui:ui:${ex["composeVersion"]}")
    implementation("androidx.compose.material:material:${ex["composeVersion"]}")
    implementation("androidx.compose.ui:ui-tooling-preview:${ex["composeVersion"]}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${ex["constraintLayoutComposeVersion"]}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${ex["composeVersion"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${ex["composeVersion"]}")
    implementation("androidx.activity:activity-compose:${ex["activityComposeVersion"]}")
    implementation("androidx.compose.material:material-icons-extended:${ex["composeVersion"]}")
    implementation("androidx.paging:paging-compose:${ex["pagingComposeVersion"]}")

    // lottie
    implementation("com.airbnb.android:lottie:${ex["lottieVersions"]}")
    implementation("com.airbnb.android:lottie-compose:${ex["lottieVersions"]}")

    // other
    implementation("com.jakewharton.timber:timber:${ex["timberVersion"]}")
    implementation("androidx.startup:startup-runtime:${ex["startupVersion"]}")
    implementation("com.google.code.gson:gson:${ex["gsonVersion"]}")
    implementation("androidx.security:security-crypto:${ex["securityCryptoVersion"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${ex["coroutinesPlayServicesVersion"]}")
}
