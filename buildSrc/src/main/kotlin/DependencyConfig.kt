import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

enum class DependencyConfig(val add: DependencyHandler.(String) -> Unit) {
    Api({ dependency -> add(API, dependency) }),

    Impl({ dependency -> add(IMPL, dependency) }),
    ModuleImpl({ dependency -> add(IMPL, project(dependency)) }),

    CompileOnly({ dependency -> add(COMPILE_ONLY, dependency) }),
    RuntimeOnly({ dependency -> add(RUNTIME_ONLY, dependency) }),

    AnnotationProcessor({ dependency -> add(ANNOTATION_PROCESSOR, dependency) })
}