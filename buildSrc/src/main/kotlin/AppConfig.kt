import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdk = 32
    const val minSdk = 26
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0.0"
    val javaVersion: JavaVersion = JavaVersion.VERSION_11
    const val jvmTarget = "11"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val proguardDefaultRules = "proguard-android.txt"
    const val proguardRules = "proguard-rules.pro"
    const val consumerProguardRules = "consumer-rules.pro"
}
