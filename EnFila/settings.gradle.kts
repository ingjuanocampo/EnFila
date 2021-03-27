pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://kotlin.bintray.com/kotlinx/")
    }
    
}
rootProject.name = "EnFila"


include(":android")
include(":domain")

