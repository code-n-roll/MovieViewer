## Features
- fetch movies by category with paging (network + database)
- favorites movies with sorting (database)
- localization (EN, RU)
- light/dark/system default theme (datastore)
  
TODO:
- movie details
- movie search
- register/login (local db)
- register/login (remote api)
- built-it player ?

## Tech practice
- Work with databases, datastore
- MVVM Compose + Hilt + Jetpack Compose Navigation
- Work with paging
- ELM implementation in Compose
  
TODO:
- testing
- multi-module architecture
  
## Tech Stack
- Kotlin
- Coroutines/Flow
- MVVM
- Compose UI
- Room/DataStore
- Retrofit + OkHttp
- Jetpack Paging 3
- Hilt
- Jetpack Compose Navigation
- Coil
- Gson

## Screenshots
<img src=https://github.com/user-attachments/assets/22e68643-7e0d-4199-998b-f2967d7540b3 width=350/>


## How to setup
1. Create local.properties in project root
2. Create API_KEY https://www.themoviedb.org/settings/api
3. Add `API_KEY = <your_api_key>` to `local.properties`


## API
TMDB API - https://developer.themoviedb.org/reference/intro/getting-started
