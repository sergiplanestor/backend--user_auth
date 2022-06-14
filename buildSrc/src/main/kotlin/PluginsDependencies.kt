object PluginsDependencies

val PluginsDependencies.kotlin: List<String> by lazy {
    listOf(
        "org.jetbrains.kotlin.jvm",
        "org.jetbrains.kotlin.plugin.spring"
    )
}
val PluginsDependencies.spring: List<String> by lazy {
    listOf(
        "org.springframework.boot",
        "io.spring.dependency-management"
    )
}