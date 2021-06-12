buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.jfrog.org/artifactory/oss-snapshot-local")

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath ("com.google.gms:google-services:4.3.8")
        classpath("io.realm.kotlin:gradle-plugin:0.0.1-SNAPSHOT")
    }
}

group = "com.ingjuanocampo.enfila"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.jfrog.org/artifactory/oss-snapshot-local")

    }
}