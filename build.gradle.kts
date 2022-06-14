import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	PluginsDependencies.kotlin.forEach(::id)
	PluginsDependencies.spring.forEach(::id)
}

group = "com.splanes.commons"

subprojects {

	logger.info("Configuring all projects, now {${name}}")

	plugin add PluginsDependencies.kotlin
	plugin add PluginsDependencies.spring

	version = "0.0.1"
	java.sourceCompatibility = JavaVersion.VERSION_11

	configurations { compileOnly { extendsFrom(configurations.annotationProcessor.get()) } }

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	dependencies {

		libs add Dependencies.kotlin
		libs add Dependencies.coroutines

		libs add Dependencies.springStarter

		libs add Dependencies.validateApi

		Dependencies.lombok.let { lombok ->
			libs compile lombok
			libs annotation lombok
		}
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}
}

dependencies {

	libs modules listOf(Web, Domain, Infra)

	libs add Dependencies.springStarter

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.springframework.security:spring-security-test")
}