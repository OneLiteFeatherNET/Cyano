rootProject.name = "cyano"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            version("minestom", "2025.07.04-1.21.5")
            version("junit", "5.13.3")
            version("junit.platform", "1.13.3")

            library("minestom","net.minestom", "minestom").versionRef("minestom")

            library("junit.api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit")
            library("junit.params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit")
            library("junit.engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
            library("junit.platform.launcher", "org.junit.platform", "junit-platform-launcher").versionRef("junit.platform")

        }
    }
}

