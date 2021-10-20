plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {

}

application {
    mainClass.set("barebones.Main")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "barebones.Main"
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
