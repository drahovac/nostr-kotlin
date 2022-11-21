import Dependencies.implementations
import Dependencies.testImplementations
import com.android.build.gradle.internal.dsl.TestOptions

plugins {
    id("com.android.application")
    kotlin("android")
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.bobmitchigan.com.android"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementations(Dependencies.composeLibraries)
    implementation(Dependencies.splashScreen)
    implementation("io.coil-kt:coil-compose:2.2.1")

    implementation(Dependencies.koinCore)
    implementation (Dependencies.koinCompose)
    implementation (Dependencies.koinAndroid)

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_ui_version"]}")
    debugImplementation("androidx.compose.ui:ui-tooling:${rootProject.extra["compose_ui_version"]}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${rootProject.extra["compose_ui_version"]}")
    testImplementations(Dependencies.testLibraries)
}
