import org.gradle.api.artifacts.dsl.DependencyHandler

const val API = "api"
const val IMPL = "implementation"
const val COMPILE_ONLY = "compileOnly"
const val RUNTIME_ONLY = "runtimeOnly"
const val ANNOTATION_PROCESSOR = "annotationProcessor"

val DependencyHandler.Web: Module by lazy { Module.Web }

val DependencyHandler.Domain: Module by lazy { Module.Domain }

val DependencyHandler.Infra: Module by lazy { Module.Infrastructure }

val DependencyHandler.libs: DependencyHandler get() = this

infix fun DependencyHandler.api(dependency: String) =
    DependencyConfig.Api.add(this, dependency)

infix fun DependencyHandler.add(dependency: String) =
    DependencyConfig.Impl.add(this, dependency)

infix fun DependencyHandler.add(dependencies: List<String>) =
    dependencies.forEach { d -> this add d }

infix fun DependencyHandler.module(module: Module) =
    DependencyConfig.ModuleImpl.add(this, module.toString())

infix fun DependencyHandler.modules(modules: List<Module>) =
    modules.forEach { m -> this module m }

infix fun DependencyHandler.compile(dependency: String) =
    DependencyConfig.CompileOnly.add(this, dependency)

infix fun DependencyHandler.runtime(dependency: String) =
    DependencyConfig.RuntimeOnly.add(this, dependency)

infix fun DependencyHandler.runtime(dependencies: List<String>) =
    dependencies.forEach { d -> this runtime d }

infix fun DependencyHandler.annotation(dependency: String) =
    DependencyConfig.AnnotationProcessor.add(this, dependency)
