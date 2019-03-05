package com.mvvm.dagger.rx.sample.main

import android.arch.lifecycle.ViewModel
import com.mvvm.dagger.rx.sample.di.ActivityScope
import com.mvvm.dagger.rx.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel: MainViewModel): ViewModel

}