plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.database"
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
}

dependencies {

    implementation(AndroidX.core)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.material)
    testImplementation(Junit.junit)
    androidTestImplementation(AndroidX.junit)
    androidTestImplementation(AndroidX.espresso)

    implementation(Dagger.dagger_lib)
    ksp(Dagger.dagger_compiler)

    implementation(Room.room)
    implementation(Room.roomRuntime)
    ksp(Room.compiler)

}