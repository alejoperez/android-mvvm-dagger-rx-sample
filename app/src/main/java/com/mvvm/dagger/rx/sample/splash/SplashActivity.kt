package com.mvvm.dagger.rx.sample.splash

import android.os.Bundle
import android.os.Handler
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.mvvm.dagger.rx.sample.R
import com.mvvm.dagger.rx.sample.base.BaseActivity
import com.mvvm.dagger.rx.sample.databinding.ActivitySplashBinding
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.livedata.Status
import com.mvvm.dagger.rx.sample.main.MainActivity
import com.mvvm.dagger.rx.sample.register.RegisterActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity<SplashViewModel,ActivitySplashBinding>() {

    companion object {
        const val SPLASH_DELAY = 2000L
    }

    override fun getLayoutId(): Int = R.layout.activity_splash
    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java
    override fun getVariablesToBind(): Map<Int, Any> = emptyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({ checkIfUserLoggedIn() }, SPLASH_DELAY)
    }

    private fun checkIfUserLoggedIn() {
        viewModel.isUserLoggedEvent.observe(this, isUserLoggedObserver)
        viewModel.isUserLoggedIn()
    }

    private val isUserLoggedObserver = Observer<Event<Boolean>> { onUserLoggedResponse(it) }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onUserLoggedResponse(response: Event<Boolean>) {
        when(response.status) {
            Status.SUCCESS -> goToNextScreen(response.peekData())
            else -> showAlert(R.string.error_splash)
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun goToNextScreen(isLogged: Boolean?) {
        isLogged?.let {
            if (it) {
                startActivity<MainActivity>()
            } else {
                startActivity<RegisterActivity>()
            }
            finish()
        }
    }

}
