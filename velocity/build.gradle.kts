plugins {
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.6.0"
}

repositories {
    gradlePluginPortal()
    maven("https://nexus.velocitypowered.com/repository/maven-public/")
}

dependencies{
    implementation(project(":common"))
    compileOnly("com.velocitypowered:velocity-api:3.1.0")
    kapt("com.velocitypowered:velocity-api:3.1.0")
}