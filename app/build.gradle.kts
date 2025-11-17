import extensions.*

plugins {
    id(Dependencies.Plugins.ANDROID_APPLICATION)
    id(Dependencies.Plugins.KOTLIN_ANDROID)
    id(Dependencies.Plugins.KOTLIN_COMPOSE)
    id(Dependencies.Plugins.KSP)
    id(Dependencies.Plugins.HILT)
    id(Dependencies.Plugins.GOOGLE_SERVICES)
}

android {
    namespace = AndroidConfig.NAMESPACE
    compileSdk = AndroidConfig.COMPILE_SDK

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID
        minSdk = AndroidConfig.MIN_SDK
        targetSdk = AndroidConfig.TARGET_SDK
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.JAVA_VERSION
        targetCompatibility = AndroidConfig.JAVA_VERSION
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.JVM_TARGET
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Feature modules
    implementationProject(":feature:home")

    // Core modules
    implementationProject(":core:ui")
    implementationProject(":core:common")
    implementationProject(":core:data")
    implementationProject(":core:domain")

    androidxCore()
    compose()
    hilt()
    testing()
    firebase()
    room()
    datastore()
    coil()
}
