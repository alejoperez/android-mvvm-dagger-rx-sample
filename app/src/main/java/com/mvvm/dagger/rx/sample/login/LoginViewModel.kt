package com.mvvm.dagger.rx.sample.login

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.mvvm.dagger.rx.sample.R
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.user.UserRepository
import com.mvvm.dagger.rx.sample.databinding.BindingAdapters
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.utils.checkField
import com.mvvm.dagger.rx.sample.utils.getValueOrDefault
import com.mvvm.dagger.rx.sample.webservice.LoginRequest
import com.mvvm.dagger.rx.sample.webservice.LoginResponse
import javax.inject.Inject

class LoginViewModel @Inject constructor(application: Application,private val userRepository: UserRepository): BaseViewModel(application) {

    val email = ObservableField("")
    val password = ObservableField("")

    val emailError = ObservableInt(BindingAdapters.EMPTY)
    val passwordError = ObservableInt(BindingAdapters.EMPTY)

    val isLoading = ObservableBoolean(false)

    private val loginEvent = MutableLiveData<Event<Unit>>()
    val loginResponse: LiveData<Event<LoginResponse>> = Transformations.switchMap(loginEvent) {
        userRepository.login(getApplication(), LoginRequest(email.getValueOrDefault(), password.getValueOrDefault()))
    }

    private fun isValidEmail(): Boolean = email.getValueOrDefault().isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email.getValueOrDefault()).matches()

    private fun isValidPassword(): Boolean = password.getValueOrDefault().isNotEmpty()

    private fun isValidForm(): Boolean = isValidEmail() && isValidPassword()

    fun login() {
        if (isValidForm()) {
            showProgress()
            loginEvent.value = Event.loading()
        } else {
            emailError.checkField(R.string.error_invalid_email,isValidEmail())
            passwordError.checkField(R.string.error_empty_password,isValidPassword())
        }
    }

    private fun showProgress() = isLoading.set(true)

    fun hideProgress() = isLoading.set(false)
}