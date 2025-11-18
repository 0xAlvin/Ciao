import extensions.*

plugins {
    id(Dependencies.Plugins.ANDROID_LIBRARY)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
    id(Dependencies.Plugins.KOTLIN_COMPOSE)
    id(Dependencies.Plugins.KSP)
    id(Dependencies.Plugins.HILT)
}

android {
    namespace = "${AndroidConfig.NAMESPACE}.feature.home"
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
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_VIEWMODEL_COMPOSE)

    compose()
    coroutines()
    hilt()
    coil()
}
