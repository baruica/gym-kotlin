plugins {
    kotlin("jvm") version "1.9.23"
    id("io.kotest") version "0.4.11"
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

val kotestVersion = "5.8.1"

dependencies {
    implementation("jp.kukv:kULID:2.0.0.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}
