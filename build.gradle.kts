plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    id("io.kotest") version "0.3.8"
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
}

group = "me.baruica"
version = "1.0"
description = "The Gym"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.0.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.0.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
