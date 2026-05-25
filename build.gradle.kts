plugins {
  kotlin("jvm") version "1.8.0"
  application
}

group = "org.edu"

version = "1.0-SNAPSHOT"

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
  implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

  testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
  testImplementation("io.kotest:kotest-assertions-core:5.5.5")
  testImplementation("io.kotest:kotest-framework-engine:5.5.5")
}

tasks.test { useJUnitPlatform() }

kotlin { jvmToolchain(17) }

application { mainClass.set("hooks.MainKt") }
