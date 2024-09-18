plugins {
  kotlin("jvm") version "1.8.0"
  application
}

group = "org.edu"

version = "1.0-SNAPSHOT"

dependencies {
  testImplementation(kotlin("test"))
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}

tasks.test { useJUnitPlatform() }

kotlin { jvmToolchain(17) }

application { mainClass.set("MainKt") }
