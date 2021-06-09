pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    
}
rootProject.name = "EnFila"


include(":android")
include(":domain")

