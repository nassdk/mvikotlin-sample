plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.test.mvikotlin_modosample")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    implementation("org.koin:koin-android:2.1.6")
    implementation("org.koin:koin-androidx-scope:2.1.6")
//    implementation("org.koin:koin-test:2.1.6")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.5.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    implementation("com.arkivanov.mvikotlin:mvikotlin:2.0.1")
    implementation("com.arkivanov.mvikotlin:mvikotlin-main:2.0.1")
    implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:2.0.1")

    implementation("com.github.terrakok:modo:0.6.1")
    implementation("com.github.terrakok:modo-render-android-fm:0.6.1")

    implementation("com.kirich1409.viewbindingpropertydelegate:viewbindingpropertydelegate:1.4.1")

    implementation("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.0")

    implementation("com.jakewharton.timber:timber:4.7.1")

    implementation("io.coil-kt:coil:1.1.1")
}