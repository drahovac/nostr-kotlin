import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependencies {

    // app level
    const val detektPluginId = "io.gitlab.arturbosch.detekt"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
    const val benManesPluginId = "com.github.ben-manes.versions"
    const val gradlePluginMaven = "https://plugins.gradle.org/m2/"

    // android ui
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint}"
    private const val composePreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    private const val composeMaterial =
        "androidx.compose.material:material:${Versions.compose}"
    private const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivity}"
    private const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.navigation}"
    private const val accompanistUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    private const val accompanistAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    const val splashScreen =
        "androidx.core:core-splashscreen:${Versions.splashScreen}"

    // compose tooling
    private const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    private const val composeManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    // koin
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    // coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    // date
    const val date = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.date}"

    // crypto
    const val hash = "com.soywiz.korlibs.krypto:krypto:${Versions.hash}"
    const val secpKmp = "fr.acinq.secp256k1:secp256k1-kmp:${Versions.secp}"
    const val secpAnd = "fr.acinq.secp256k1:secp256k1-kmp-jni-android:${Versions.secp}"
    const val secpJvm = "fr.acinq.secp256k1:secp256k1-kmp-jni-jvm:${Versions.secp}"

    // serial
    const val serial = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.seri}"

    // test libs
    const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val composeUnit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    private const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    private const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    private const val jodaTest = "joda-time:joda-time:${Versions.joda}"

    val composeLibraries = listOf(
        coreKtx,
        appcompat,
        composeUi,
        composePreview,
        composeMaterial,
        composeNavigation,
        composeActivity,
        composeConstraintLayout,
    )

    val accompanistLibraries = listOf(
        accompanistUiController,
        accompanistAnimation,
    )

    val composeDebugTooling = listOf(
        composeTooling,
        composeManifest
    )

    val testLibraries = listOf(
        junit,
        composeUnit,
        robolectric,
        mockk,
        espressoCore,
        coroutinesTest,
        jodaTest,
    )

    val androidTestLibraries = listOf(
        extJUnit,
    )

    fun DependencyHandlerScope.implementations(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandlerScope.implementationModules(vararg name: String) {
        name.forEach {
            add("implementation", project(mapOf("path" to it)))
        }
    }


    fun DependencyHandler.testImplementationModules(vararg name: String) {
        name.forEach {
            add("testImplementation", project(mapOf("path" to it)))
        }
    }

    fun DependencyHandler.debugImplementations(list: List<String>) {
        list.forEach { dependency ->
            add("debugImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementations(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementations(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.detektPlugins(dependency: String) {
        add("detektPlugins", dependency)
    }
}
