package com.mvvm.dagger.rx.sample.splash

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.user.UserRepository
import com.mvvm.dagger.rx.sample.livedata.Event
import javax.inject.Inject

class SplashViewModel @Inject constructor(application: Application, private val userRepository: UserRepository): BaseViewModel(application) {

    val isUserLoggedEvent = MutableLiveData<Event<Boolean>>()

    fun isUserLoggedIn() {
        isUserLoggedEvent.value = Event.success(userRepository.isLoggedIn(getApplication()))
    }

}