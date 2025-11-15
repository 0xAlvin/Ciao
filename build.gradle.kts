plugins {
    id(Dependencies.Plugins.ANDROID_APPLICATION) version Versions.AGP apply false
    id(Dependencies.Plugins.KOTLIN_ANDROID) version Versions.KOTLIN apply false
    id(Dependencies.Plugins.KOTLIN_COMPOSE) version Versions.KOTLIN apply false
    id(Dependencies.Plugins.HILT) version Versions.HILT apply false
    id(Dependencies.Plugins.KSP) version Versions.KSP apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
