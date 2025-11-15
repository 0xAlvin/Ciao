import extensions.*

plugins {
    id(Dependencies.Plugins.ANDROID_LIBRARY)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
    id(Dependencies.Plugins.KOTLIN_COMPOSE)
}

android {
    namespace = "${AndroidConfig.NAMESPACE}.core.ui"
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AndroidConfig.MIN_SDK
    }

    buildFeatures {
        compose = true
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
    implementationProject(":core:common")

    implementation(Dependencies.AndroidX.CORE_KTX)
    compose()
}
