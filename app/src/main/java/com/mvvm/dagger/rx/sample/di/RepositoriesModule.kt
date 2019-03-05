package com.mvvm.dagger.rx.sample.di

import com.mvvm.dagger.rx.sample.data.photos.PhotosRepositoryModule
import com.mvvm.dagger.rx.sample.data.places.PlacesRepositoryModule
import com.mvvm.dagger.rx.sample.data.user.UserRepositoryModule
import dagger.Module

@Module(
        includes = [
            UserRepositoryModule::class,
            PlacesRepositoryModule::class,
            PhotosRepositoryModule::class
        ]
)
abstract class RepositoriesModule