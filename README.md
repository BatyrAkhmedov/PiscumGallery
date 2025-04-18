# 📸 Gallery App

An Android application that displays images from the [Picsum API](https://picsum.photos/) using MVVM architecture and modern Android development tools.

## ✨ Features

- Fetches images from `https://picsum.photos`
- Displays images in a `StaggeredGridLayout`
- Full-screen image dialog on click
- Pull-to-refresh support
- Loading indicators for images
- Error handling during image loading
- Loads a random page of images each time

## 🧱 Architecture & Stack

- **MVVM** – clean separation of concerns
- **Dagger 2** – dependency injection
- **Retrofit + RxJava** – network requests with reactive style
- **Glide + Picasso** – efficient image loading
- **LiveData** and **ViewModel** – reactive UI updates
- **SwipeRefreshLayout** – swipe to refresh the gallery
- **RecyclerView (StaggeredGrid)** – adaptive image grid layout

## 🧠 Core Components

- `ImageViewModel` – loads data, handles errors, manages loading state
- `RepositoryImpl` – interacts with `PicsumApiService`, maps DTOs to domain models
- `ImageAdapter` – binds and displays images in a grid
- `ImageLoadingListener` – handles image loading progress visibility
- `MainActivity` – observes data and handles UI & interactions