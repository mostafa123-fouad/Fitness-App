// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
// Add the Google services Gradle plugin
    id("com.google.gms.google-services")version "4.4.2" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.0" apply false // Add Compose compiler plugin
}