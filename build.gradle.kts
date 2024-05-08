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
    implementation("com.discord4j:discord4j-core:3.2.6")
}

tasks.test {
    useJUnitPlatform()
}