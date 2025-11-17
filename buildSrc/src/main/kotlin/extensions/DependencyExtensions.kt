package extensions

import Dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency: Any) {
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


fun DependencyHandler.implementationPlatform(dependency: String) {
    add("implementation", platform(dependency))
}

fun DependencyHandler.androidTestImplementationPlatform(dependency: String) {
    add("androidTestImplementation", platform(dependency))
}


fun DependencyHandler.implementationProject(path: String) {
    add("implementation", project(mapOf("path" to path)))
}

//fun DependencyHandler.apiProject(path: String) {
//    add("api", project(mapOf("path" to path)))
//}


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
    implementation(Dependencies.Compose.MATERIAL_ICONS_EXTENDED)
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
fun DependencyHandler.firebase() {
    implementation(platform(Dependencies.Firebase.FIREBASE_BOM)) // REQUIRED
    implementation(Dependencies.Firebase.FIREBASE_AUTH)
    implementation(Dependencies.Firebase.FIREBASE_ANALYTICS)
}
fun DependencyHandler.room(){
    implementation(Dependencies.Room.ROOM)
    implementation(Dependencies.Room.PAGING_3)
    implementation(Dependencies.Room.ROOM_COROUTINES)
    implementation(Dependencies.Room.ROOM_TEST_HELPER)
}
fun DependencyHandler.datastore(){
    implementation(Dependencies.DataStore.DATASTORE)
}
fun DependencyHandler.coil(){
    implementation(Dependencies.Coil.COIL_COMPOSE)
    implementation(Dependencies.Coil.COIL_NETWORK)
}

