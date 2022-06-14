@file:Suppress("UnstableApiUsage")

pluginManagement {
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val kotlinVersion: String by settings

    repositories {
        maven(url = "./maven-repo")
        gradlePluginPortal()
        ivy(url = "./ivy-repo")
        mavenCentral()
    }

    plugins {
        id("org.springframework.boot") version springBootVersion//"2.6.8"
        id("io.spring.dependency-management") version springDependencyManagementVersion //"1.0.11.RELEASE"
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

rootProject.name = "user_auth"
include(":infrastructure")
include(":domain")
include(":web")