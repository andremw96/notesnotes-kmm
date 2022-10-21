buildscript {
    extra.apply {
        set("androidAppCompatVersion", "1.5.1")
        set("androidMaterialVersion", "1.7.0")
        set("activityComposeVersion", "1.6.0")
        set("androidComposeUiVersion", "1.2.1")
        set("androidComposeLiveDataVersion", "1.3.0-rc01")
        set("multiDexVersion", "1.0.3")
        set("androidComposeNavVersion", "2.5.2")

        // koin
        set("koinVersion", "3.2.2")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.0").apply(false)
    id("com.android.library").version("7.3.0").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
