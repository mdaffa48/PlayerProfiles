plugins {
    id ("java")
    id ("maven-publish")
    id ("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
     maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
     maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
     maven("https://maven.enginehub.org/repo/")
     maven("https://nexus.sirblobman.xyz/repository/public/")
     maven("https://jitpack.io")
     maven("https://repo.codemc.org/repository/maven-public/")
     maven("https://repo.maven.apache.org/maven2")
}
dependencies {
    compileOnly("com.github.aglerr:MCLibs:0.1.1")
    compileOnly("org.codemc.worldguardwrapper:worldguardwrapper:1.2.0-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.10")
    compileOnly("com.SirBlobman.combatlogx:CombatLogX-API:10.0.0.0-SNAPSHOT")
    compileOnly(fileTree(mapOf("dir" to "deps", "include" to listOf("*.jar"))))
}

tasks {
    shadowJar {
        relocate("org.codemc.worldguardwrapper", "com.github.aglerr.worldguardwrapper")
    }
}
