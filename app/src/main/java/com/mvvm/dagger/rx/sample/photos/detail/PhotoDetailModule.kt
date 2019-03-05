package com.mvvm.dagger.rx.sample.photos.detail

import android.arch.lifecycle.ViewModel
import com.mvvm.dagger.rx.sample.di.FragmentScope
import com.mvvm.dagger.rx.sample.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PhotoDetailModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeFragment(): PhotoDetailDialogFragment

    @Binds
    @IntoMap
    @ViewModelKey(PhotoDetailViewModel::class)
    abstract fun bindViewModel(viewModel: PhotoDetailViewModel): ViewModel
}