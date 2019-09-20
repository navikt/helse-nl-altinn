import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val coroutines_version = "1.3.1"
val ktor_version = "1.2.4"
val logback_version = "1.2.3"
val prometheus_version = "1.2.1"
val spek_version = "2.0.7"
val logstash_encoder_version = "6.2"
val jackson_version = "2.9.9"
val kluent_version = "1.54"

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.40"
    id("com.diffplug.gradle.spotless") version "3.14.0"
    id("com.github.johnrengelman.shadow") version "4.0.3"
}

group = "no.nav.helse.nl"
version = "1.0-SNAPSHOT"

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "no.nav.helse.nl.ApplicationKt"
}

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/ktor")
    maven(url = "https://repo.adeo.no/repository/maven-releases/")
    maven(url = "https://dl.bintray.com/spekframework/spek-dev")
    maven(url = "https://dl.bintray.com/kotlin/kotlinx/")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
    testLogging.showStandardStreams = true
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:$coroutines_version")
    implementation("io.ktor:ktor-metrics-micrometer:$ktor_version")
    implementation("io.micrometer:micrometer-registry-prometheus:$prometheus_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
//    implementation("io.ktor:ktor-client-apache:$ktor_version")
//    implementation("io.ktor:ktor-client-logging:$ktor_version")
//    implementation("io.ktor:ktor-client-logging-jvm:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstash_encoder_version")
//    implementation("com.fasterxml.jackson.core:jackson-databind:$jackson_version")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jackson_version")
//    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.1")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.11.1")

//    implementation("io.ktor:ktor-jackson:$ktor_version")
//    implementation("io.ktor:ktor-client-jackson:$ktor_version")
//    implementation("io.ktor:ktor-auth:$ktor_version")
//    implementation("io.ktor:ktor-auth-jwt:$ktor_version")

    testImplementation("org.amshove.kluent:kluent:$kluent_version")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")

    testRuntimeOnly("org.spekframework.spek2:spek-runtime-jvm:$spek_version")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")

    api("io.ktor:ktor-client-mock:$ktor_version")
    api("io.ktor:ktor-client-mock-jvm:$ktor_version")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    create("printVersion") {
        println(version)
    }
}
