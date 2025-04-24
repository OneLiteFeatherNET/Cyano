rootProject.name = "cyano"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            version("minestom", "a1d1920a04")
            version("junit-jupiter", "5.12.2")

            library("minestom","net.minestom", "minestom-snapshots").versionRef("minestom")

            library("junit-api", "org.junit.jupiter", "junit-jupiter-api").versionRef("junit-jupiter")
            library("junit-params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit-jupiter")
        }
    }
}

