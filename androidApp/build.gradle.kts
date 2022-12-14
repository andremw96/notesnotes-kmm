plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.andremw96.notesnotes_kmm.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.andremw96.notesnotes_kmm.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))

    val androidAppCompatVersion = rootProject.extra["androidAppCompatVersion"]
    val androidMaterialVersion = rootProject.extra["androidMaterialVersion"]
    val activityComposeVersion = rootProject.extra["activityComposeVersion"]
    val androidComposeUiVersion = rootProject.extra["androidComposeUiVersion"]
    val multiDexVersion = rootProject.extra["multiDexVersion"]
    val androidComposeLiveDataVersion = rootProject.extra["androidComposeLiveDataVersion"]
    val koinVersion = rootProject.extra["koinVersion"]
    val navVersion = rootProject.extra["androidComposeNavVersion"]
    val coroutineVersion = rootProject.extra["coroutineVersion"]
    val androidComposeConstraintLayoutVersion = rootProject.extra["androidComposeConstraintLayoutVersion"]
    val gsonVersion = rootProject.extra["gsonVersion"]
    val accompanistSwipeRefresh = rootProject.extra["accompanistSwipeRefresh"]

    implementation("androidx.appcompat:appcompat:$androidAppCompatVersion")
    implementation("com.google.android.material:material:$androidMaterialVersion")
    implementation("androidx.compose.ui:ui:$androidComposeUiVersion")
    implementation("androidx.compose.ui:ui-tooling:$androidComposeUiVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$androidComposeUiVersion")
    implementation("androidx.compose.foundation:foundation:$androidComposeUiVersion")
    implementation("androidx.compose.material:material:$androidComposeUiVersion")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation("com.android.support:multidex:$multiDexVersion")
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:$androidComposeConstraintLayoutVersion")
    implementation("androidx.compose.material:material-icons-extended:$androidComposeUiVersion")

    // compose live data
    implementation("androidx.compose.runtime:runtime-livedata:$androidComposeLiveDataVersion")

    // koin
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation ("io.insert-koin:koin-androidx-compose:$koinVersion")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")

    // swipe refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanistSwipeRefresh")

    // gson
    implementation("com.google.code.gson:gson:$gsonVersion")
}
