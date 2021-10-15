plugins {
    val kotlinVersion = "1.5.31"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("io.kotest") version "0.3.8"
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

val kotestVersion = "4.6.3"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
