plugins {
    kotlin("jvm") version "2.0.10"
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // dependencies for logging
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.16")

    //to implement color through text, located on the Kotlin website from a GitHub repository.
    implementation("com.github.ajalt.mordant:mordant:3.0.1")
    // optional extensions for running animations with coroutines
    implementation("com.github.ajalt.mordant:mordant-coroutines:3.0.1")
    // optional widget for rendering Markdown
    implementation("com.github.ajalt.mordant:mordant-markdown:3.0.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}