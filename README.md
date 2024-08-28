# Android Project

Welcome to the **Android Project**! This project showcases a comprehensive Android application demonstrating the integration of various modern Android development libraries and tools.

## Table of Contents

- [Features](#features)
- [Libraries Used](#libraries-used)
- [Architecture](#architecture)
- [Branches](#branches)
- [Getting Started](#getting-started)
- [Installation](#installation)

## Features

- **API Integration**: Fetch data using Retrofit for network operations.
- **Dependency Injection**: Hilt is used for dependency injection, making the code more modular and easier to test.
- **Declarative UI**: Jetpack Compose is used for building a declarative UI.
- **Navigation**: Jetpack Navigation Component is implemented for seamless navigation between different pages.
- **Call Functionality**: Built-in support for making phone calls directly from the app.
- **Image Loading**: Coil library is used for efficient image loading and caching.
- **Offline Support**: Room Database is implemented for local data storage and offline support.

## Libraries Used

The project makes use of the following libraries:

- [**Retrofit**](https://square.github.io/retrofit/): A type-safe HTTP client for Android and Java to consume REST APIs.
- [**Hilt**](https://developer.android.com/training/dependency-injection/hilt-android): A dependency injection library for Android that reduces the boilerplate of manual dependency injection.
- [**Jetpack Compose**](https://developer.android.com/jetpack/compose): Android’s modern toolkit for building native UI.
- [**Navigation Component**](https://developer.android.com/guide/navigation): A framework for navigating between app pages.
- [**Coil**](https://coil-kt.github.io/coil/): An image loading library for Android backed by Kotlin Coroutines.
- [**Room Database**](https://developer.android.com/training/data-storage/room): A persistence library providing an abstraction layer over SQLite.
- [**Kotlin Coroutines**](https://kotlinlang.org/docs/coroutines-overview.html): A core library for
  asynchronous programming using Coroutines.
- [**RxJava**](https://github.com/ReactiveX/RxJava): A library for composing asynchronous and
  event-based programs using observable sequences.

## Architecture

This project follows the **MVVM (Model-View-ViewModel)** architecture pattern, ensuring a clear separation of concerns and a more modular, testable, and maintainable codebase.

## Branches

- **Main Branch**: Implements asynchronous calls using **Kotlin Coroutines (Flows)** for a more
  modern and streamlined approach.
- **RxJava Branch**: Implements asynchronous calls using **Reactive Extensions (RxJava)** to
  demonstrate a reactive programming model.

To switch between branches, use:

```bash
git checkout main
```

Or

```bash
git checkout rxjava
```

## Getting Started

To get a local copy up and running, follow these simple steps:

### Installation

1. **Clone the repo**

```bash
   git clone https://github.com/rajdeepf1/CarfaxAndroidTechnicalAssignment.git
```

2. **Open the project in Android Studio**

   - Go to `File -> Open -> Navigate to the project directory`

3. **Sync the project with Gradle files**

   - Allow Android Studio to download the required dependencies.

