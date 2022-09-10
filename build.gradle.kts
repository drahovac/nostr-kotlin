import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_JAVA
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_SRC_DIR_KOTLIN
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_TEST_SRC_DIR_JAVA
import io.gitlab.arturbosch.detekt.extensions.DetektExtension.Companion.DEFAULT_TEST_SRC_DIR_KOTLIN

plugins {
    id(Dependencies.benManesPluginId) version Versions.benManes
    id("io.gitlab.arturbosch.detekt") version "1.21.0"
}

buildscript {
    val compose_ui_version by extra("1.1.1")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven(url = Dependencies.gradlePluginMaven)
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detekt}")
        classpath("com.squareup.sqldelight:gradle-plugin:${Versions.database}")
    }
}

allprojects {
    group = "io.gitlab.arturbosch.detekt"
    apply(plugin = "io.gitlab.arturbosch.detekt")

    repositories {
        google()
        mavenCentral()
    }

    detekt {
        source = objects.fileCollection().from(
            DEFAULT_SRC_DIR_JAVA,
            DEFAULT_TEST_SRC_DIR_JAVA,
            DEFAULT_SRC_DIR_KOTLIN,
            DEFAULT_TEST_SRC_DIR_KOTLIN,
            "shared/src/commonMain/kotlin",
            "shared/src/commonTest/kotlin"
        )
        buildUponDefaultConfig = true
        baseline = file("$rootDir/config/detekt/baseline.xml")
    }

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        jvmTarget = "1.8"
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(true)
            sarif.required.set(true)
        }
    }
    tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
        jvmTarget = "1.8"
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
