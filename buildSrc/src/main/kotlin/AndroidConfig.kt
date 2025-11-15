import org.gradle.api.JavaVersion

object AndroidConfig {
    const val NAMESPACE = "com.example.ciao"
    const val APPLICATION_ID = "com.example.ciao"

    const val COMPILE_SDK = Versions.COMPILE_SDK
    const val MIN_SDK = Versions.MIN_SDK
    const val TARGET_SDK = Versions.TARGET_SDK

    const val VERSION_CODE = Versions.VERSION_CODE
    const val VERSION_NAME = Versions.VERSION_NAME

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    val JAVA_VERSION = JavaVersion.VERSION_11
    const val JVM_TARGET = "11"
}
