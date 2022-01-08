plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("io.kotest") version "0.3.8"
}

group = "me.baruica"
version = "1.0"
description = "The Gym"

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.0.3")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.0.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
