object Versions {
    val appcompat = "1.6.1"
    val constraintLayout = "2.1.4"
    val dagger = "2.50"
    val core = "1.12.0"
    val material = "1.11.0"
    val espresso = "3.5.1"
    val junit = "4.13.2"
    val androidx_junit = "1.1.5"
    val circleindicator = "2.1.6"
    val navigation = "2.7.7"
    val fragmentKtx = "1.6.2"
    val retrofit = "2.9.0"
    val okhttp3 = "4.12.0"
    val room = "2.6.1"
    val glide = "4.16.0"
    val adapterdelegates = "4.3.2"
    val decoro = "1.5.2"
}

object Junit {
    val junit = "junit:junit:${Versions.junit}"
}
object AndroidX {
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val core = "androidx.core:core-ktx:${Versions.core}"
    val material = "com.google.android.material:material:${Versions.material}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    object Navigation {
        val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }
}

object Dagger {
    val dagger_lib = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object CircleIndicator {
    val lib = "me.relex:circleindicator:${Versions.circleindicator}"
}

object Retrofit {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
}

object Room {
    val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    val room = "androidx.room:room-ktx:${Versions.room}"
    val compiler ="androidx.room:room-compiler:${Versions.room}"
}

object Glide {
    val lib = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object AdapterDelegates {
    val lib = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl:${Versions.adapterdelegates}"
}

object Decoro {
    val lib = "ru.tinkoff.decoro:decoro:${Versions.decoro}"
}
