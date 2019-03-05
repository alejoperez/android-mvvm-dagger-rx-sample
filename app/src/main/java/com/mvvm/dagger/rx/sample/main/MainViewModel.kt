package com.mvvm.dagger.rx.sample.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.room.User
import com.mvvm.dagger.rx.sample.data.user.UserRepository
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.utils.addTo
import com.mvvm.dagger.rx.sample.utils.applyIoAndMainThreads
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application, private val userRepository: UserRepository) : BaseViewModel(application) {

    val user = MutableLiveData<Event<User>>()

    val onLogoutSuccess = MutableLiveData<Event<Unit>>()

    fun getUser() {

        userRepository.getUser(getApplication())
                .applyIoAndMainThreads()
                .subscribe(
                        {
                            user.value = Event.success(it)
                        },
                        {
                            user.value = Event.failure()
                        })
                .addTo(compositeDisposable)

        user.value = Event.loading()
    }

    fun logout() {
        userRepository.logout(getApplication())
                .applyIoAndMainThreads()
                .subscribe(
                        {
                            onLogoutSuccess.value = Event.success(Unit)
                        },
                        {
                            onLogoutSuccess.value = Event.failure()
                        })
                .addTo(compositeDisposable)
    }

}