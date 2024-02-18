plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.onlinestore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.onlinestore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(AndroidX.core)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    implementation(AndroidX.constraintLayout)
    testImplementation(Junit.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso)

    implementation(AndroidX.Navigation.fragment)
    implementation(AndroidX.Navigation.ui)

    implementation(Dagger.dagger_lib)
    ksp(Dagger.dagger_compiler)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter)
    implementation(Retrofit.logging)

    implementation(project(":core"))
    implementation(project(":database"))
    implementation(project(":feature_details"))
    implementation(project(":setting_provider"))
    implementation(project(":remote"))
    implementation(project(":feature_registration"))
    implementation(project(":feature_home"))
    implementation(project(":feature_catalog"))
    implementation(project(":feature_bag"))
    implementation(project(":feature_discount"))
    implementation(project(":feature_account"))
    implementation(project(":feature_favorites"))
}