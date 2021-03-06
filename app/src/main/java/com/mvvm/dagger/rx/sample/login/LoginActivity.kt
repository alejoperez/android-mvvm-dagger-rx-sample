package com.mvvm.dagger.rx.sample.login

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.mvvm.dagger.rx.sample.R
import com.mvvm.dagger.rx.sample.base.BaseActivity
import com.mvvm.dagger.rx.sample.databinding.ActivityLoginBinding
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.livedata.Status
import com.mvvm.dagger.rx.sample.main.MainActivity
import com.mvvm.dagger.rx.sample.utils.EditTextUtils
import com.mvvm.dagger.rx.sample.webservice.LoginResponse
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_login
    override fun getViewModelClass(): Class<LoginViewModel> = LoginViewModel::class.java
    override fun getVariablesToBind(): Map<Int, Any> = mapOf(
            BR.viewModel to viewModel,
            BR.etUtils to EditTextUtils
    )

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loginEvent.observe(this, onLoginResponseObserver)
    }

    private val onLoginResponseObserver = Observer<Event<LoginResponse>> { onLoginResponse(it) }

    private fun onLoginResponse(response: Event<LoginResponse>) {
        when (response.status) {
            Status.SUCCESS -> onLoginSuccess()
            Status.FAILURE -> onLoginFailure()
            Status.NETWORK_ERROR -> onNetworkError()
            else -> Unit
        }
    }

    private fun onLoginSuccess() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    private fun onLoginFailure() = showAlert(R.string.error_invalid_credentials)
}
