object Dependencies {

    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
        const val LIFECYCLE_VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.LIFECYCLE}"
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE}"
    }

    object Compose {
        const val BOM = "androidx.compose:compose-bom:${Versions.COMPOSE_BOM}"
        const val UI = "androidx.compose.ui:ui"
        const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
        const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        const val UI_TEST_MANIFEST = "androidx.compose.ui:ui-test-manifest"
        const val UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4"
        const val MATERIAL3 = "androidx.compose.material3:material3"
        const val MATERIAL_ICONS_EXTENDED = "androidx.compose.material:material-icons-extended"
    }

    object Navigation {
        const val COMPOSE = "androidx.navigation:navigation-compose:${Versions.NAVIGATION}"
    }

    object Hilt {
        const val ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
        const val NAVIGATION_COMPOSE = "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPOSE}"
    }

    object Coroutines {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"
    }

    object Testing {
        const val JUNIT = "junit:junit:${Versions.JUNIT}"
        const val JUNIT_EXT = "androidx.test.ext:junit:${Versions.JUNIT_EXT}"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
        const val COROUTINES = Coroutines.TEST
    }

    object Plugins {
        const val ANDROID_APPLICATION = "com.android.application"
        const val ANDROID_LIBRARY = "com.android.library"
        const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
        const val KOTLIN_COMPOSE = "org.jetbrains.kotlin.plugin.compose"
        const val HILT = "com.google.dagger.hilt.android"
        const val KSP = "com.google.devtools.ksp"
        const val GOOGLE_SERVICES = "com.google.gms.google-services"
    }
    object Firebase {
        const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
        const val FIREBASE_AUTH = "com.google.firebase:firebase-auth"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics"
    }
    object Room {
        const val ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
        const val ROOM_COROUTINES = "androidx.room:room-ktx:${Versions.ROOM}"
        const val PAGING_3 = "androidx.room:room-paging:${Versions.ROOM}"
        const val ROOM_TEST_HELPER = "androidx.room:room-testing:${Versions.ROOM}"
    }
}
