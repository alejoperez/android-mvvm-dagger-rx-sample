package com.mvvm.dagger.rx.sample.splash

import android.arch.lifecycle.ViewModel
import com.mvvm.dagger.rx.sample.di.ActivityScope
import com.mvvm.dagger.rx.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeActivity(): SplashActivity

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindViewModel(viewModel: SplashViewModel): ViewModel

}