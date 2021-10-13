plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.14.3")
}

application {
    mainClass.set("urlreader.Main")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "urlreader.Main"
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
