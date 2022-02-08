plugins {
    kotlin("jvm") version("1.6.0")
    id("com.github.johnrengelman.shadow") version "7.1.1"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.3"
}

group = "run.dn5"
version = "0.1-BETA"
description = "サーバーで起きたいろいろな出来事をDiscordのWebHookで通知します。Paper(Paper派生), Waterfall, Velocityで使用できます。"

val artifactName =  "${rootProject.name}-${rootProject.version}.jar"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(project(":paper"))
    implementation(project(":waterfall"))
    implementation(project(":velocity"))
    implementation(project(":common"))
}

tasks {
    shadowJar{
        archiveFileName.set(artifactName)
    }

    register("copyJar"){
        dependsOn("clean", "shadowJar")
        doLast {
            listOf("paper", "waterfall", "velocity").forEach {
                copy {
                    from("$buildDir/libs/${artifactName}")
                    into(".debug/$it/plugins")
                }
            }
        }
    }
    register("preDebug"){
        dependsOn("clean", "shadowJar", "copyJar")
    }
}

subprojects {
    group = parent!!.group
    version = parent!!.version
    description = parent!!.description

    apply {
        plugin("org.jetbrains.gradle.plugin.idea-ext")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("java")
        plugin("com.github.johnrengelman.shadow")
    }

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        compileOnly("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks {
        processResources {
            filesMatching(listOf("plugin.yml", "bungee.yml")) {
                expand(mapOf(
                    "name" to project.name,
                    "version" to project.version,
                    "description" to project.description,
                    "author" to "ddPn08"
                ))
            }
        }
        compileKotlin {
            kotlinOptions.jvmTarget = "17"
        }
    }
}