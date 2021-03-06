import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    kotlin("jvm") version "1.4.31"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

version = "1.0.0"

repositories {
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public/")
}

val shadowImplementation: Configuration by configurations.creating
configurations["implementation"].extendsFrom(shadowImplementation)

dependencies {
    shadowImplementation(kotlin("stdlib"))
    implementation("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    shadowImplementation("com.github.sya-ri:EasySpigotAPI:1.6.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
}

configure<KtlintExtension> {
    version.set("0.40.0")
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowImplementation)
    archiveClassifier.set("")
}

configure<BukkitPluginDescription> {
    name = project.name
    version = project.version.toString()
    main = "com.github.syari.plgin.helloworld.Main"
    apiVersion = "1.16"
    author = "sya_ri"
}
