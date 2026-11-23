plugins {
	java
	id("org.springframework.boot") version "4.1.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.Micro-Service"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2025.1.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-starter-gateway-server-webflux")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.boot:spring-boot-starter-data-redis-reactive-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testCompileOnly("org.projectlombok:lombok")
	testAnnotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
