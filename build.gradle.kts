plugins {
  java
  id("org.springframework.boot") version "3.5.7"
  id("io.spring.dependency-management") version "1.1.7"
}
val springCloudVersion by extra("2025.0.0")

group = "dev.cuong.smartbookstore"
version = "0.0.1-SNAPSHOT"
description = "catalog-service"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(25)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.cloud:spring-cloud-starter-config")
  implementation("org.springframework.retry:spring-retry")
  implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
  implementation("org.flywaydb:flyway-database-postgresql")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.testcontainers:postgresql")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  runtimeOnly("org.postgresql:postgresql")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
