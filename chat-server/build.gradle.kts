plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":chat-common"))
    implementation("com.google.code.gson:gson:2.8.9")
}

application {
    mainClass.set("chat.server.Main")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "chat.server.Main"
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

