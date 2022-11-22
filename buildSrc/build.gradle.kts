
plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("module-plugin") {
            id = "module-plugin"
            implementationClass = "CommonLibraryModulePlugin"
        }
        register("app-plugin") {
            id = "app-plugin"
            implementationClass = "CommonApplicationModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation(kotlin("gradle-plugin", "1.7.0"))
}
