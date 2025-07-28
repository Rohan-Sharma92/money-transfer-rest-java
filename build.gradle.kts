import nu.studer.gradle.jooq.JooqGenerate
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

val flywayVersion: String = "11.10.4"
val jooqVersion = "3.18.7"

buildscript {
	dependencies {
		classpath("org.postgresql:postgresql:42.7.7")
		classpath("org.testcontainers:postgresql:1.17.6")
		classpath("org.jooq:jooq-codegen:3.18.7")
		classpath("org.flywaydb:flyway-database-postgresql:11.10.4")
	}
}

plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	id("nu.studer.jooq") version "8.2" // jOOQ plugin
	id("org.flywaydb.flyway") version "11.10.4"
}

group = "com.rohansharma"
version = "0.0.1-SNAPSHOT"


val postgreSQLDriver = "org.postgresql.Driver"
val postgresDockerImageName: DockerImageName = DockerImageName.parse("postgres:16")
val postgreSQLContainer = PostgreSQLContainer<Nothing>(postgresDockerImageName).apply {
	withDatabaseName("databaseName")
	withUsername("user")
	withPassword("pass")
	start()
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven("https://repo.flywaydb.org")
}

dependencies {
	// Spring Boot core
	implementation("org.springframework.boot:spring-boot-starter")

	// Spring Boot JDBC and JOOQ
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.jooq:jooq")

	// PostgreSQL Driver
	runtimeOnly("org.postgresql:postgresql")
	jooqGenerator("org.postgresql:postgresql")
	jooqGenerator("org.jooq:jooq")
	jooqGenerator("org.jooq:jooq-meta")

	// Flyway for migrations
	implementation("org.flywaydb:flyway-core:$flywayVersion")
	implementation("org.flywaydb:flyway-database-postgresql:$flywayVersion")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	// Testcontainers for PostgreSQL
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	url = postgreSQLContainer.jdbcUrl
	driver = postgreSQLDriver
	user = postgreSQLContainer.username
	password = postgreSQLContainer.password
	baselineOnMigrate = true
	locations = arrayOf("filesystem:src/main/resources/db/migration")
}

jooq {
	version.set(jooqVersion) // or match your jOOQ version
	configurations {
		create("main") {
			generateSchemaSourceOnCompilation.set(true)
			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.WARN
				jdbc.apply {
					driver = postgreSQLDriver
					url = postgreSQLContainer.jdbcUrl
					user = postgreSQLContainer.username
					password = postgreSQLContainer.password
				}
				generator.apply {
					name = "org.jooq.codegen.DefaultGenerator"
					database.apply {
						name = "org.jooq.meta.postgres.PostgresDatabase"
						inputSchema = "public"
					}
					target.apply {
						packageName = "com.example.jooq.generated"
						directory = "$buildDir/generated-src/jooq/main"
					}
				}
			}
		}
	}
}

tasks.withType<JooqGenerate>{
	dependsOn(tasks.flywayMigrate)
	allInputsDeclared.set(true)
	doLast {
		postgreSQLContainer.stop()
	}
}

// Include generated sources in main
sourceSets["main"].java {
	srcDir("$buildDir/generated-src/jooq/main")
}
