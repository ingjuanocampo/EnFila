buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath ("com.google.gms:google-services:4.3.8")
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

    }
}