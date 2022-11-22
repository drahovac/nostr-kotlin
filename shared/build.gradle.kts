import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version Versions.kotlin
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        macosArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    sourceSets {
        val ktorVersion = "2.0.3"
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-websockets:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:1.2.11")
                api("co.touchlab:kermit:1.1.3")
                implementation(Dependencies.koinCore)
                implementation(Dependencies.hash)
                implementation(Dependencies.serial)
                api(Dependencies.date)
                implementation(Dependencies.dbMain)
                implementation(Dependencies.dbCor)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation(Dependencies.secpKmp) // missing iosArm64 support https://github.com/ACINQ/secp256k1-kmp/issues/54
                implementation(Dependencies.dbAnd)
                implementation(Dependencies.secpAnd)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Dependencies.secpJvm)
                implementation(Dependencies.mockk)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation(Dependencies.dbIos)
            }
        }
        val macosArm64Main by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation(Dependencies.dbIos)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("EventDatabase") {
        packageName = "com.bobmitchigan"
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}
