import org.gradle.api.Action
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

internal fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

internal fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

internal fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}

internal fun DependencyHandler.releaseImplementation(depName: String) {
    add("releaseImplementation", depName)
}

internal fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

internal fun DependencyHandler.annotationProcessor(depName: String) {
    add("annotationProcessor", depName)
}

internal fun DependencyHandler.implementationByExclude(
    depName: String,
    dependencyConfiguration: Action<ExternalModuleDependency>
) {
    addDependencyTo(this, "implementation", depName, dependencyConfiguration)
}
