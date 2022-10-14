package com.andremw96.notesnotes_kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform