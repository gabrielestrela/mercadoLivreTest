plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.serialization)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
}