plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version("6.1.0")
    id("fr.il_totore.manadrop") version("0.4-SNAPSHOT")
}

group = "ru.entryset"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies { 

    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")

    compileOnly(files("C:\\Users\\t9154\\Desktop\\sources\\EntryEconomy\\build\\libs\\EntryEconomy-1.0.0-all.jar"))
    compileOnly(files("C:\\Users\\t9154\\Desktop\\sources\\EntryCore\\build\\libs\\EntryCore-1.0.0-all.jar"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks {
    jar {
        enabled = false
        dependsOn(shadowJar)
    }
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}