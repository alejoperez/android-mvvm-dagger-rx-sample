package com.mvvm.dagger.rx.sample.main

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.room.User
import com.mvvm.dagger.rx.sample.data.user.UserRepository
import com.mvvm.dagger.rx.sample.livedata.Event
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application, private val userRepository: UserRepository) : BaseViewModel(application) {

    private val getUserEvent = MutableLiveData<Event<Unit>>()

    val user: LiveData<Event<User>> = Transformations.switchMap(getUserEvent) {
        userRepository.getUser(getApplication())
    }

    val onLogoutSuccess = MutableLiveData<Event<Unit>>()

    fun getUser() {
        getUserEvent.value = Event.loading()
    }

    fun logout() {
        onLogoutSuccess.value = Event.success(userRepository.logout(getApplication()))
    }

}