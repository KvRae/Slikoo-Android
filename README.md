
# Slikoo


![logo](https://github.com/KvRae/Slikoo-Android/assets/58667227/94fee035-631a-498e-93ca-22f6b113e490)


## Table of Contents
This README contains the following sections:

1. [About Slikoo](#about-slikoo)
2. [Features](#features)
3. [Installation](#installation)
4. [Architecture](#architecture)
5. [Project tree](#project-tree)
6. [Project structure](#project-structure)
7. [Screenshots](#screenshots)
8. [Download the app](#download-the-app)
9. [Dependencies](#dependencies)
10. [Contributing](#contributing)
11. [License](#license)
12. [Contact](#contact)


## About Slikoo

Slikoo is an Android application that aims to be the ultimate platform for culinary encounters.
We believe in the power of shared meals to create connections and promote cultural diversity.
With Slikoo, you can discover new flavors, meet culinary enthusiasts, and expand your culinary horizons.

## Features

- **Discover New Flavors**: Explore a wide variety of recipes from around the world and discover new flavors to tantalize your taste buds.

- **Meet Culinary Enthusiasts**: Connect with like-minded individuals who share your passion for food and cooking. Exchange ideas, tips, and recipes with the community.

- **Expand Your Culinary Horizons**: Slikoo offers a diverse range of recipes, cooking techniques, and culinary traditions. Expand your cooking skills and try out new dishes from different cultures.

- **Food-Loving Community**: Join our vibrant community of food lovers who appreciate the joy and artistry of food. Share your culinary creations, learn from others, and build lasting friendships.

## Installation

To use Slikoo, follow these steps:

1. Clone the repository: `git clone https://github.com/KvRae/Slikoo-Android.git`
2. Open the project in Android Studio.
3. Build and run the project on an Android device or emulator.

## Architecture

Slikoo follows the [Model-View-ViewModel (MVVM)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) architecture pattern. 
![mvvm](https://github.com/KvRae/Slikoo-Android/assets/58667227/bdc86f23-7342-4016-abcb-6e584d1fe1bb)

The MVVM pattern is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view) 
from the development of the business logic or back-end logic (the model) so that the view is not dependent on any specific model platform. 
The view model of MVVM is a value converter, meaning the view model is responsible for exposing (converting) the data objects from the model in such a way 
that objects are easily managed and presented. In this respect, the view model is more model than view and handles most if not all of the view's display logic. 
The view model may implement a mediator pattern, organizing access to the back-end logic around the set of use cases supported by the view.


## Project tree

```
.
├── app
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src
│       ├── androidTest
│       │   └── java
│       │       └── com
│       │           └── example
│       │               └── slikoo
│       │                   └── ExampleInstrumentedTest.kt
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── slikoo
│       │   │               ├── data
│       │   │               │   ├── api
│       │   │               │   │   ├── ApiHelper.kt
│       │   │               │   │   ├── ApiServiceImpl.kt
│       │   │               │   │   └── model
│       │   │               │   │       ├── Category.kt
│       │   │               │   │       ├── CategoryResponse.kt
│       │   │               │   │       ├── Recipe.kt
│       │   │               │   │       ├── RecipeResponse.kt
│       │   │               │   │       ├── RecipeSearchResponse.kt
│       │   │               │   │       ├── RecipeSearchResult.kt
│       │   │               ├── ui
│       │   │               │   ├── base
│       │   │               │   │   ├── BaseActivity.kt
│       │   │               │   │   └── BaseFragment.kt
│       │   │               │   ├── category
│       │   │               │   │   ├── CategoryAdapter.kt
```

## Project structure 
The project is structured in the following way:
package | description
--- | ---
`data` | Contains the data accessing and manipulating components.
`ui` | Contains classes related to the UI.
`utils` | Contains utility classes.
`viewmodels` | Contains the ViewModels.
`models` | Contains the Models.
`api` | Contains the API service and helper.
`repositories` | Contains the Repositories.
`fragments` | Contains the Fragments.
`pages` | Contains the Activities.
`base` | Contains the base classes.

## Screenshots
| Home Screen                                                                                                  | Recipe Screen                                                                                                  | Recipe Details Screen                                                                                                  |
|--------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|
| ![Home Screen](https://github.com/KvRae/Slikoo-Android/assets/58667227/929b54c0-09d7-43e3-bec1-7d902c9885a2) | ![Recipe Screen](https://github.com/KvRae/Slikoo-Android/assets/58667227/a5589b44-672c-456b-9d26-8e252d9d4c55) | ![Recipe Details Screen](https://github.com/KvRae/Slikoo-Android/assets/58667227/a5589b44-672c-456b-9d26-8e252d9d4c55) |

## Download the app

You can download the latest version of Slikoo from both:
[Google Play Store](https://play.google.com/store/apps/details?id=com.example.slikoo)  
[HUAWEI AppGallery](https://www.amazon.com/dp/B08Z3JQZQD).



## Dependencies

Slikoo relies on the following dependencies:

- [Jetpack Compose](https://developer.android.com/jetpack/compose): A modern toolkit for building native Android UI.
- [Room](https://developer.android.com/jetpack/androidx/releases/room): A persistence library that provides an abstraction layer over SQLite.
- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for making API calls.
- [Courotines](https://developer.android.com/kotlin/coroutines): A library for asynchronous programming in Kotlin.
- [Coil](https://github.com/bumptech/glide): A fast and efficient image loading library for Android.
- [Gson](https://github.com/google/gson): A Java library for converting Java objects to JSON and vice-versa.
- [Kotlin Coroutines]() A library for asynchronous programming in Kotlin.
- [Dagger Hilt](https://dagger.dev/hilt/): A dependency injection library for Android.
- [Jetpack Navigation](https://developer.android.com/guide/navigation): A library for implementing navigation in Android apps.
- [accompanist]() A collection of extension libraries for Jetpack Compose.
- [JUnit](https://junit.org/junit5/): A unit testing framework for Java and Kotlin.
- [Espresso](https://developer.android.com/training/testing/espresso): A testing framework for testing Android apps.


You can find the full list of dependencies and their versions in the `build.gradle` file.

## Contributing

We welcome contributions to Slikoo! If you would like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch: `git checkout -b feature/your-feature-name`.
3. Make your changes and commit them: `git commit -am 'Add some feature'`.
4. Push the changes to your branch: `git push origin feature/your-feature-name`.
5. Create a pull request detailing your changes.

Please make sure to adhere to the [code style guidelines](https://github.com/slikoo/style-guide) and write tests for your code.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

If you have any questions, suggestions, or feedback, please don't hesitate to contact us at [slikoo@example.com](mailto:slikoo@example.com). We would love to hear from you!

Visit our website at [www.slikoo.com](https://www.slikoo.com) to learn more about Slikoo and join our community.

Let's celebrate food, encounters, and friendship together with Slikoo!
