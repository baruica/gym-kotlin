plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("io.kotest") version "0.4.10"
}

group = "me.baruica"
version = "1.0"
description = "The Gym"

repositories {
    mavenCentral()
}

val kotestVersion = "5.6.2"

dependencies {
    implementation("com.github.guepardoapps:kulid:2.0.0.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
