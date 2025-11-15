package extensions

import Dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

// Basic dependency configurations
fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.ksp(dependency: String) {
    add("ksp", dependency)
}

// Platform dependencies
fun DependencyHandler.implementationPlatform(dependency: String) {
    add("implementation", platform(dependency))
}

fun DependencyHandler.androidTestImplementationPlatform(dependency: String) {
    add("androidTestImplementation", platform(dependency))
}

// Project dependencies
fun DependencyHandler.implementationProject(path: String) {
    add("implementation", project(mapOf("path" to path)))
}

fun DependencyHandler.apiProject(path: String) {
    add("api", project(mapOf("path" to path)))
}

// Grouped dependencies for convenience
fun DependencyHandler.androidxCore() {
    implementation(Dependencies.AndroidX.CORE_KTX)
    implementation(Dependencies.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.AndroidX.ACTIVITY_COMPOSE)
}

fun DependencyHandler.compose() {
    implementationPlatform(Dependencies.Compose.BOM)
    implementation(Dependencies.Compose.UI)
    implementation(Dependencies.Compose.UI_GRAPHICS)
    implementation(Dependencies.Compose.UI_TOOLING_PREVIEW)
    implementation(Dependencies.Compose.MATERIAL3)
    debugImplementation(Dependencies.Compose.UI_TOOLING)
    debugImplementation(Dependencies.Compose.UI_TEST_MANIFEST)
}

fun DependencyHandler.composeNavigation() {
    implementation(Dependencies.Navigation.COMPOSE)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.Hilt.ANDROID)
    ksp(Dependencies.Hilt.COMPILER)
    implementation(Dependencies.Hilt.NAVIGATION_COMPOSE)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Coroutines.ANDROID)
    testImplementation(Dependencies.Coroutines.TEST)
}

fun DependencyHandler.testing() {
    testImplementation(Dependencies.Testing.JUNIT)
    testImplementation(Dependencies.Testing.COROUTINES)
    androidTestImplementation(Dependencies.Testing.JUNIT_EXT)
    androidTestImplementation(Dependencies.Testing.ESPRESSO_CORE)
    androidTestImplementationPlatform(Dependencies.Compose.BOM)
    androidTestImplementation(Dependencies.Compose.UI_TEST_JUNIT4)
}
