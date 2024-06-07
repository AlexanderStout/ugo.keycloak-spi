plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.ugo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val keycloakVersion = "24.0.5"

dependencies {
    testImplementation(kotlin("test"))

    // Keycloak
    implementation("org.keycloak:keycloak-core:$keycloakVersion")
    implementation("org.keycloak:keycloak-server-spi:$keycloakVersion")
    implementation("org.keycloak:keycloak-server-spi-private:$keycloakVersion")

    // Tools
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")


    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}