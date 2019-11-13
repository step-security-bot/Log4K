plugins {
    kotlin("jvm") version "1.3.50"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks {
    val ensureSecretsExist by registering {
        val secretFile = File("$rootDir/src/main/kotlin/Secrets.kt")
        description = "Ensures that $secretFile exists"

        doFirst {
            if (!secretFile.exists()) {
                secretFile.writeText(
                    """
object Secrets {
    object Artifactory {
        const val username = ""
        const val password = ""
    }
}

""".trimIndent()
                )
            }
        }
    }
    named("assemble") { dependsOn(ensureSecretsExist) }
}
