package com.mvvm.dagger.rx.sample.application

import com.mvvm.dagger.rx.sample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class SampleApp: DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val graph = DaggerAppComponent.builder().application(this).build()
        graph.inject(this)
        return graph
    }

}