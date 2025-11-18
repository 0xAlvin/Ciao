import extensions.*

plugins {
    id(Dependencies.Plugins.ANDROID_LIBRARY)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
    id(Dependencies.Plugins.KSP)
    id(Dependencies.Plugins.HILT)
}

android {
    namespace = "${AndroidConfig.NAMESPACE}.core.data"
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.JAVA_VERSION
        targetCompatibility = AndroidConfig.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }
}

dependencies {
    implementationProject(":core:domain")

    implementation(Dependencies.AndroidX.CORE_KTX)
    coroutines()
    hilt()
    testing()
}
