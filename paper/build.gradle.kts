repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}
dependencies{
    implementation(project(":common"))
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT")
}
