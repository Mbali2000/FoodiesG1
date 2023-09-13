//plugins {
//    id("com.android.application")
//    id("org.jetbrains.kotlin.android")
//    id("com.google.gms.google-services")
//}
//
//android {
//    namespace = "com.example.foodiesg1"
//    compileSdk = 33
//
//    defaultConfig {
//        applicationId = "com.example.foodiesg1"
//        minSdk = 24
//        targetSdk = 33
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
//
//    buildFeatures{
//        viewBinding = true
//    }
//}
//
//dependencies {
//
//    implementation("androidx.core:core-ktx:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.9.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
//    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
//    implementation("androidx.navigation:navigation-fragment:2.7.2")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//
//    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
//
//    // Add the dependency for the Realtime Database library
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-database-ktx")
//
//}


plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.example.foodiesg1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodiesg1"
        minSdk =21
        targetSdk = 33
        versionCode =1
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
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//dependencies {
//
//    implementation ("androidx.core:core-ktx:1.7.0")
//    implementation ("androidx.appcompat:appcompat:1.5.1")
//    implementation ("com.google.android.material:material:1.6.1")
//    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
//
//    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
//
//    // Add the dependency for the Realtime Database library
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-database-ktx")
//
//
//    // Import the BoM for the Firebase platform
//
//    implementation ("com.google.firebase:firebase-auth:21.0.8")
//    testImplementation ("junit:junit:4.13.2")
//    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
//
//    //navigation
//
//    def nav_version = "2.5.2"
//    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
//    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
//}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
    implementation("com.google.firebase:firebase-auth-ktx:22.1.1")
    implementation("androidx.navigation:navigation-fragment:2.7.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))

    // Add the dependency for the Realtime Database library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-database-ktx")

}