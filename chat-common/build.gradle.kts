plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.8.9")
}

task<Wrapper>("wrapper") {
    gradleVersion = "7.2"
}
