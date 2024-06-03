plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.selogu"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.selogu"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("androidx.room:room-ktx:2.6.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("androidx.room:room-runtime:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

}




//plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.jetbrainsKotlinAndroid)
//    id("com.google.devtools.ksp")
//}
//
//android {
//    namespace = "com.example.selogu"
//    compileSdk = 34
//
//    defaultConfig {
//        applicationId = "com.example.selogu"
//        minSdk = 24
//        targetSdk = 34
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        viewBinding = true;
//    }
//}
//
//dependencies {

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    implementation("com.squareup.retrofit2:retrofit:2.6.2")
//    implementation("com.squareup.retrofit2:converter-gson:2.6.2")
//    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
//    implementation("com.github.bumptech.glide:glide:4.11.0")
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

//    val roomVersion = "2.2.6"
//    implementation ("androidx.room:room-runtime:$roomVersion")
//    annotationProcessor ("androidx.room:room-compiler:$roomVersion")
//    androidTestImplementation ("androidx.room:room-testing:$roomVersion")
//    implementation( "androidx.room:room-ktx:$roomVersion")
//    kapt ("androidx.room:room-compiler:2.5.2")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
//    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0")

//    implementation("androidx.datastore:datastore-preferences:1.0.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
//    implementation("androidx.activity:activity-ktx:1.8.2")
//    implementation("androidx.fragment:fragment-ktx:1.6.2")
//    implementation("com.github.bumptech.glide:glide:4.16.0")
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
//    implementation("androidx.room:room-runtime:2.5.2")
//    ksp("androidx.room:room-compiler:2.5.2")
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)

//}