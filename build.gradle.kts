import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "8.1.0"
    id("xyz.jpenilla.run-paper") version "2.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.3"
}

group = "dev.byzus"
version = "1.2.0-SNAPSHOT"
description = "ByzusCells"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    maven { url = uri("https://oss.sonatype.org/content/groups/public/") }
    maven { url = uri("https://repo.panda-lang.org/releases/") }
    maven { url = uri("https://repo.panda-lang.org/snapshots/") }
    maven { url = uri("https://repo.eternalcode.pl/releases") }
    maven { url = uri("https://repo.eternalcode.pl/snapshots") }
    maven { url = uri("https://repository.minecodes.pl/snapshots") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    implementation("dev.rollczi.litecommands:bukkit:2.8.5")
    implementation("org.panda-lang:expressible:1.3.1")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    api("org.jetbrains:annotations:24.0.1")
    implementation("org.panda-lang:panda-utilities:0.5.2-alpha")
    implementation("dev.triumphteam:triumph-gui:3.1.5")

}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true
}

tasks.withType<ShadowJar> {
    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "org/checkerframework/**",
        "META-INF/**",
        "javax/**"
    )

    mergeServiceFiles()
    minimize()

}

bukkit {
    name = "ByzusCells"
    version = "1.0.0-SNAPSHOT"
    description = "ByzusCells"
    authors = listOf("Byzus")
    website = "https://github.com/Byzus/ByzusCells"
    apiVersion = "1.13"
    main = "dev.byzus.byzuscells.ByzusCells"
    prefix = "ByzusCells"
}


tasks {
    runServer {
        minecraftVersion("1.19.4")
    }
}
