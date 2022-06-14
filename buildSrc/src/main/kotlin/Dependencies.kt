object Dependencies

val Dependencies.kotlin: List<String> by lazy {
    listOf(
        "org.jetbrains.kotlin:kotlin-reflect",
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8",
    )
}
val Dependencies.coroutines: List<String> by lazy {
    listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core",
        "org.jetbrains.kotlinx:kotlinx-coroutines-reactor",
    )
}
val Dependencies.springWeb: List<String> by lazy {
    listOf(
        "org.springframework.boot:spring-boot-starter-security",
        //"org.springframework.boot:spring-boot-starter-web",
        "org.springframework.boot:spring-boot-starter-webflux",
    )
}
val Dependencies.r2db: List<String> by lazy {
    listOf(
        "io.projectreactor.kotlin:reactor-kotlin-extensions",
        "io.projectreactor.spring:reactor-spring-core:2.0.7.RELEASE",
        "io.projectreactor:reactor-core:3.4.18",
        "org.springframework.boot:spring-boot-starter-data-r2dbc",
    )
}
val Dependencies.jwtRuntime: List<String> by lazy {
    listOf(
        "io.jsonwebtoken:jjwt-jackson:0.11.5",
        "io.jsonwebtoken:jjwt-impl:0.11.5",
    )
}
val Dependencies.springStarter: String by lazy { "org.springframework.boot:spring-boot-starter" }
val Dependencies.jwt: String by lazy { "io.jsonwebtoken:jjwt-api:0.11.5" }
val Dependencies.swagger: String by lazy { "org.springdoc:springdoc-openapi-ui:1.6.8" }
val Dependencies.mariadb: String by lazy { "org.mariadb:r2dbc-mariadb" }
val Dependencies.lombok: String by lazy { "org.projectlombok:lombok" }
val Dependencies.validateApi: String by lazy { "javax.validation:validation-api" }
