package com.mvvm.dagger.rx.sample.register

import androidx.lifecycle.ViewModel
import com.mvvm.dagger.rx.sample.di.ActivityScope
import com.mvvm.dagger.rx.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class RegisterModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeActivity(): RegisterActivity

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindViewModel(viewModel: RegisterViewModel): ViewModel

}