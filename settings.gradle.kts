rootProject.name = "cyano"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            version("minestom", "a1d1920a04")
            version("junit", "5.13.2")
            version("junit.platform", "1.13.1")

            library("minestom","net.minestom", "minestom-snapshots").versionRef("minestom")

            library("junit.api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit")
            library("junit.params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit")
            library("junit.engine", "org.junit.jupiter", "junit-jupiter-engine").versionRef("junit")
            library("junit.platform.launcher", "org.junit.platform", "junit-platform-launcher").versionRef("junit.platform")


        }
    }
}

