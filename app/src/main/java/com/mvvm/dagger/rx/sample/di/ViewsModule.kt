package com.mvvm.dagger.rx.sample.di

import com.mvvm.dagger.rx.sample.login.LoginModule
import com.mvvm.dagger.rx.sample.main.MainModule
import com.mvvm.dagger.rx.sample.photos.PhotosModule
import com.mvvm.dagger.rx.sample.photos.detail.PhotoDetailModule
import com.mvvm.dagger.rx.sample.places.PlacesModule
import com.mvvm.dagger.rx.sample.register.RegisterModule
import com.mvvm.dagger.rx.sample.splash.SplashModule
import dagger.Module

@Module(
        includes = [
            SplashModule::class,
            RegisterModule::class,
            LoginModule::class,
            MainModule::class,
            PlacesModule::class,
            PhotosModule::class,
            PhotoDetailModule::class
        ]
)
abstract class ViewsModule