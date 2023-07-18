// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
}
true // Needed to make the Suppress annotation work for the plugins block

buildscript {
    dependencies {
        classpath(libs.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.kotlin.serialization)
//        classpath(libs.shadowJar.gradle.plugin)
//        classpath(libs.cklib.gradle.plugin)
        classpath(libs.zipline.gradle.plugin)
    }
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}