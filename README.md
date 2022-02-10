# Flickr Photo Search

[![Build Status](https://app.bitrise.io/app/9fb9c3d8e513a1e7/status.svg?token=TLNlhodjG5Oi0PNwNRa8Sg&branch=development)](https://app.bitrise.io/app/9fb9c3d8e513a1e7)

Flickr Photo Search is an Android project using [Flickr API](https://www.flickr.com/services/api/) based on [MVVM architecture](https://developer.android.com/jetpack/guide).
You can search photos based on keywords and view some details about the selected photo.

![Showcase](/docs/showcase.png)

## Features
- 100% Kotlin
- MVVM architecture
- Reactive pattern
- Android Jetpack
- Kotlin Coroutines + Flow
- Single activity app with fragments
- Dependency injection
- CI/CD support

## Tech stack
- [Android Jetpack](https://developer.android.com/jetpack) - A collections of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices.
    - [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - Extend the functionality of Dagger Hilt to enable dependency injection of certain classes from the androidx libraries.
    - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Lifecycle-aware components that can adjust behavior based on the current lifecycle state of an activity or fragment.
    - [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - Build and structure your in-app UI, handle deep links, and navigate between screens.
    - [Paging](https://developer.android.com/jetpack/androidx/releases/paging) - Load data in pages, and present it in a RecyclerView.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room) - Create, store, and manage persistent data backed by a SQLite database.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Coil](https://coil-kt.github.io/coil/) - Image loading for Android backed by Kotlin Coroutines.
- [Logcat](https://github.com/square/logcat) - Tiny Kotlin API for cheap logging on top of Android's normal Log class.
- [PhotoView](https://github.com/Baseflow/PhotoView) - Implementation of ImageView for Android that supports zooming, by various touch gestures.
- [Bitrise](https://www.bitrise.io) - Mobile-first CI/CD in the cloud, for any platform.


## Development setup
You need [Android Studio Arctic Fox](https://developer.android.com/studio) (or newer) to be able to build the app.

### Code style
This project uses [ktlint](https://github.com/pinterest/ktlint). Use the `ktlint` and `ktlint-format` Gradle tasks
to check and format according to proper Kotlin lint rules.

### API key :key:
You will need to provide an API key to use the app.
You can find information about how to gain access in the following link.
- [Flickr](https://www.flickr.com/services/apps/create/apply/)

Add the key to the `gradle.properties` file:

```
# Flickr API key
FLICKR_API_KEY=key
```
