plugins {
    kotlin("jvm") version("1.6.0")
    id("com.github.johnrengelman.shadow") version "7.1.1"
}

group = "run.dn5"
version = "1.0"
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
}

tasks {
    shadowJar{
        archiveFileName.set(artifactName)
    }

    register("preDebug"){
        dependsOn("clean", "shadowJar")
        listOf("paper", "waterfall", "velocity").forEach {
            copy {
                from("$buildDir/libs/${artifactName}")
                into(".debug/$it/plugins")
            }
        }
    }
}

subprojects {
    group = parent!!.group
    version = parent!!.version

    apply {
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
                expand(mapOf("version" to project.version, "description" to project.description))
            }
        }
        compileKotlin {
            kotlinOptions.jvmTarget = "17"
        }
    }
}