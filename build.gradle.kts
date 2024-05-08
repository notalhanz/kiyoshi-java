plugins {
    id("java")
}

group = "net.alhanz"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.javacord:javacord:3.8.0")
}

tasks.test {
    useJUnitPlatform()
}