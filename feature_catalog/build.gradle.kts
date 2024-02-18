plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.catalog"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(AndroidX.fragmentKtx)

    implementation(CircleIndicator.lib)

    implementation(Glide.lib)

    implementation(AdapterDelegates.lib)

    implementation(Dagger.dagger_lib)
    ksp(Dagger.dagger_compiler)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.converter)
    implementation(Retrofit.logging)

    implementation(project(":core"))
    implementation(project(":remote"))
    implementation(project(":database"))
    implementation(project(":feature_details"))
}