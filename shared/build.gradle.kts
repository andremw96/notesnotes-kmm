plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-kapt")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                val koinVersion = rootProject.extra["koinVersion"]
                // Koin Core features
                api("io.insert-koin:koin-core:$koinVersion")
                api("io.insert-koin:koin-test:$koinVersion")
                // Koin Test features
                //testImplementation("io.insert-koin:koin-test:3.2.2")
//                // Koin for Ktor
//                implementation("io.insert-koin:koin-ktor:3.2.2")
//                // SLF4J Logger
//                implementation("io.insert-koin:koin-logger-slf4j:3.2.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.andremw96.notesnotes_kmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}
