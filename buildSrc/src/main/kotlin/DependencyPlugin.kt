import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

val Project.plugin: DependencyPlugin get() = DependencyPlugin(this)

data class DependencyPlugin(val project: Project)

infix fun DependencyPlugin.add(plugin: String) {
    project.apply(plugin = plugin)
}

infix fun DependencyPlugin.add(plugins: List<String>) {
    plugins.forEach(::add)
}