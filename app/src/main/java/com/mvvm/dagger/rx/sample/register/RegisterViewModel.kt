package com.mvvm.dagger.rx.sample.register

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.mvvm.dagger.rx.sample.R
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.user.UserRepository
import com.mvvm.dagger.rx.sample.databinding.BindingAdapters
import com.mvvm.dagger.rx.sample.utils.checkField
import com.mvvm.dagger.rx.sample.utils.getValueOrDefault
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.webservice.RegisterRequest
import com.mvvm.dagger.rx.sample.webservice.RegisterResponse
import javax.inject.Inject

class RegisterViewModel @Inject constructor(application: Application, private val userRepository: UserRepository): BaseViewModel(application) {

    val name = ObservableField("")
    val email = ObservableField("")
    val password = ObservableField("")

    val errorName = ObservableInt(BindingAdapters.EMPTY)
    val errorEmail = ObservableInt(BindingAdapters.EMPTY)
    val errorPassword = ObservableInt(BindingAdapters.EMPTY)

    val isLoading = ObservableField(false)

    private val registerEvent = MutableLiveData<Event<Unit>>()

    val registerResponse: LiveData<Event<RegisterResponse>> = Transformations.switchMap(registerEvent) {
        userRepository.register(getApplication(), RegisterRequest(name.getValueOrDefault(), email.getValueOrDefault(), password.getValueOrDefault()))
    }

    fun register() {
        if (isValidForm()) {
            showProgress()
            registerEvent.value = Event.loading()
        } else {
            errorName.checkField(R.string.error_name_empty,isValidName())
            errorEmail.checkField(R.string.error_invalid_email,isValidEmail())
            errorPassword.checkField(R.string.error_empty_password,isValidPassword())
        }
    }

    private fun isValidName(): Boolean = name.getValueOrDefault().isNotEmpty()

    private fun isValidEmail(): Boolean = email.getValueOrDefault().isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email.getValueOrDefault()).matches()

    private fun isValidPassword(): Boolean = password.getValueOrDefault().isNotEmpty()

    private fun isValidForm(): Boolean = isValidName() && isValidEmail() && isValidPassword()

    private fun showProgress() = isLoading.set(true)

    fun hideProgress() = isLoading.set(false)
}