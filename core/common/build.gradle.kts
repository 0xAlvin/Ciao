import extensions.*

plugins {
    id(Dependencies.Plugins.ANDROID_LIBRARY)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "${AndroidConfig.NAMESPACE}.core.common"
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
    implementation(Dependencies.AndroidX.CORE_KTX)

    coroutines()
}
