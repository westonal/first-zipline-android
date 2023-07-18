//import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("app.cash.zipline")
}

kotlin {
    // Later can target iOS
//    iosArm64()
//    iosX64()
//    iosSimulatorArm64()

    android()

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.zipline)
            }
        }
        // TODO: what is "host"?
        val hostMain by creating {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.zipline.loader)
                api(libs.okio.core)
            }
        }

        val androidMain by getting {
            dependsOn(hostMain)
            dependencies {
                implementation(libs.okHttp.core)
            }
        }
//        val iosMain by creating {
//            dependsOn(hostMain)
//        }
//        targets.withType<KotlinNativeTarget> {
//            val main by compilations.getting
//            main.defaultSourceSet.dependsOn(iosMain)
//        }
    }
}

android {
    namespace = "com.nytimes.firstzipline.lib"
    compileSdk = 33
}
