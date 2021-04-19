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

    implementAndroidX()
    implementMviKotlin()
    implementDi()
    implementNetworking()
    implementNavigationHelper()

    implementation(Deps.kotlin)
    implementation(Deps.coroutines)
    implementation(Deps.viewBindingDelegate)
    implementation(Deps.timber)
    implementation(Deps.adapterDelegate)
    implementation(Deps.material)
    implementation(Deps.coil)
}