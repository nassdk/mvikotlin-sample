import org.gradle.api.artifacts.dsl.DependencyHandler

object Deps {

    object AndroidX {
        const val core = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT}"
    }

    object MviKotlin {
        const val core = "com.arkivanov.mvikotlin:mvikotlin:${Versions.MVI_KOTLIN}"
        const val main = "com.arkivanov.mvikotlin:mvikotlin-main:${Versions.MVI_KOTLIN}"
        const val coroutinesExt =
            "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${Versions.MVI_KOTLIN}"
    }

    object Modo {
        const val core = "com.github.terrakok:modo:${Versions.MODO}"
        const val render = "com.github.terrakok:modo-render-android-fm:${Versions.MODO}"
    }

    object Koin {
        const val android = "org.koin:koin-android:${Versions.KOIN}"
        const val scope = "org.koin:koin-androidx-scope:${Versions.KOIN}"
    }

    object OkHttp {
        const val core = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP3}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP3}"
    }

    object KotlinxSerialization {
        const val runtime =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.KOTLINX_SERIALIZATION_RUNTIME}"
        const val retrofitConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.KOTLINX_SERIALIZATION_CONVERTER}"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"

    const val material = "com.google.android.material:material:${Versions.MATERIAL}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"

    const val viewBindingDelegate =
        "com.kirich1409.viewbindingpropertydelegate:viewbindingpropertydelegate:${Versions.VIEWBINDING_DELEGATE}"

    const val adapterDelegate =
        "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.ADAPTER_DELEGATE}"

    const val timber = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    const val coil = "io.coil-kt:coil:${Versions.COIL}"
}

fun DependencyHandler.implementAndroidX() {
    implementation(Deps.AndroidX.core)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.constraint)
}

fun DependencyHandler.implementMviKotlin() {
    implementation(Deps.MviKotlin.core)
    implementation(Deps.MviKotlin.main)
    implementation(Deps.MviKotlin.coroutinesExt)
}

fun DependencyHandler.implementNetworking() {
    implementation(Deps.OkHttp.core)
    implementation(Deps.OkHttp.logging)
    implementation(Deps.KotlinxSerialization.runtime)
    implementation(Deps.KotlinxSerialization.retrofitConverter)
    implementation(Deps.retrofit)
}

fun DependencyHandler.implementDi() {
    implementation(Deps.Koin.android)
    implementation(Deps.Koin.scope)
}

fun DependencyHandler.implementNavigationHelper() {
    implementation(Deps.Modo.core)
    implementation(Deps.Modo.render)
}