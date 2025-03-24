plugins {
    kotlin("jvm") version "2.1.10"
    id("io.kotest") version "0.4.11"
    id("com.autonomousapps.dependency-analysis") version "2.13.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "me.baruica"
version = "1.0"
description = "The Gym"

repositories {
    mavenCentral()
}

val kotestVersion = "5.9.1"

dependencies {
    testImplementation("jp.kukv:kULID:2.0.0.1")
    testRuntimeOnly("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-shared:$kotestVersion")
    testImplementation("io.kotest:kotest-common:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-api:$kotestVersion")
}
