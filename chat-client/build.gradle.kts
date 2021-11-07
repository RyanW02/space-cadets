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
    mainClass.set("chat.client.Main")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "chat.client.Main"
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

task<Wrapper>("wrapper") {
    gradleVersion = "7.2"
}
