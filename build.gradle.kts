plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    id("io.kotest") version "0.3.9"
}

group = "me.baruica"
version = "1.0"
description = "The Gym"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.guepardoapps:kulid:2.0.0.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.3.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.3.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
