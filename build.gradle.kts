
// Top-level build file where you can add configuration options common to all sub-projects/modules.


plugins {
    alias(libs.plugins.androidApplication) apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("com.google.devtools.ksp") version "1.8.22-1.0.11" apply false
}
//plugins {
//    alias(libs.plugins.androidApplication) apply false
//    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
//    id("com.google.devtools.ksp") version "1.8.22-1.0.11" apply false
//}
//
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        // Define classpath dependencies for the build script
//        classpath("com.android.tools.build:gradle:7.1.3")
//        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
//    }
//}