plugins {
    kotlin("jvm") version "2.1.21"
    kotlin("plugin.serialization") version "1.9.0"
    id("maven-publish")
}

group = "net.veloxia.logis"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
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
