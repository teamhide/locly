import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("jacoco")
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.asciidoctor.jvm.convert") version "3.3.2"
	id("com.diffplug.spotless") version "6.20.0"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	kotlin("plugin.jpa") version "1.9.20"
}

group = "com.fitlog"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

spotless {
	val excludeFiles = arrayOf(".idea/**/*.*", ".vscode/**/*.*")

	java {
		removeUnusedImports()
		replaceRegex(
				"Remove wildcard imports",
				"import\\s+[^\\*\\s]+\\*;(\\r\\n|\\r|\\n)",
				"$1"
		)
		googleJavaFormat()
		importOrder(
				"java",
				"jakarta",
				"javax",
				"lombok",
				"org.springframework",
				"",
				"\\#",
				"org.junit",
				"\\#org.junit",
				"com.fitlog",
				"\\#com.fitlog"
		)
		indentWithTabs(1)
		indentWithSpaces(4)
		trimTrailingWhitespace()
		endWithNewline()
	}
	format("yaml") {
		target("**/*.yaml", "**/*.yml")
		targetExclude(*excludeFiles)
		prettier().configFile(rootDir.absolutePath + "/.prettierrc")
	}
	format("xml") {
		target("**/*.xml")
		targetExclude(*excludeFiles)
		prettier().config(mapOf("parser" to "html", "printWidth" to 160))
				.configFile(rootDir.absolutePath + "/.prettierrc")
	}
	format("md") {
		target("**/*.md")
		targetExclude(*excludeFiles)
		prettier().config(mapOf("printWidth" to 160)).configFile(rootDir.absolutePath + "/.prettierrc")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

val testAll by tasks.registering {
	dependsOn("spotlessCheck", "test", "jacocoTestReport", "jacocoTestCoverageVerification")
	tasks["test"].mustRunAfter(tasks["spotlessCheck"])
	tasks["jacocoTestReport"].mustRunAfter(tasks["test"])
	tasks["jacocoTestCoverageVerification"].mustRunAfter(tasks["jacocoTestReport"])
}

val preSpotless by tasks.registering {
	dependsOn("spotlessCheck", "spotlessApply")
	finalizedBy(tasks.jacocoTestReport)
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks.test {
	useJUnitPlatform()
	systemProperties["spring.profiles.active"] = "test"
	outputs.dir(snippetsDir)
}

tasks.register("testUnit", Test::class) {
	useJUnitPlatform()
	systemProperties["spring.profiles.active"] = "test"
	exclude("**/*ControllerTest*")
}

tasks.register("teste2e", Test::class) {
	useJUnitPlatform()
	systemProperties["spring.profiles.active"] = "test"
	include("**/*ControllerTest*")
}

tasks.asciidoctor {
	inputs.dir(snippetsDir)
	doFirst {
		delete {
			file("src/main/resources/static/docs")
		}
	}
}

val copyHTML by tasks.registering(Copy::class) {
	dependsOn(tasks.asciidoctor)
	from(file("build/docs/asciidoc"))
	into(file("src/main/resources/static/docs"))
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		html.required.set(true)
		xml.required.set(false)
		csv.required.set(false)
	}
	finalizedBy(tasks.jacocoTestCoverageVerification)
	classDirectories.setFrom(
			files(classDirectories.files.map {
				fileTree(it) {
					exclude(
							"**/*Application*",
							"**/Q*Entity*",
							"**/decoder/AuthTokenDecoder*",
							"**/healthcheck/CustomHealthIndicator*",
							"**/config/AwsConfig*"
					)
				}
			})
	)
}

tasks.jacocoTestCoverageVerification {
	val queryDslClasses = ('A'..'Z').map { "*.Q${it}*" }
	violationRules {
		rule {
			element = "CLASS"
			limit {
				counter = "LINE"
				value = "COVEREDRATIO"
				minimum = "1.00".toBigDecimal()
			}
			classDirectories.setFrom(sourceSets.main.get().output.asFileTree)
			excludes = listOf(
					"com.fitlog.fitlog.common.AwsConfig",
			) + queryDslClasses
		}
	}
}
