plugins {
    application
    id("com.github.johnrengelman.shadow") version ("8.1.1")
    id("java")
}

application.mainClass = "net.alhanz.kiyoshi.MyBot"
group = "net.alhanz"
version = "1.0-ALPHA"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.javacord:javacord:3.8.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
}

