plugins {
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "8.4"
}

group = "net.veloxia.logis"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.VeloxiaNW"
            artifactId = "Logis"
            version = "1.0.0"

            from(components["java"])
        }
    }
}