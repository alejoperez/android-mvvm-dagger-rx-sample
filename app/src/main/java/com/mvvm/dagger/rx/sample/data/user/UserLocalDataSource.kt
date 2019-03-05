package com.mvvm.dagger.rx.sample.data.user

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.User
import com.mvvm.dagger.rx.sample.data.preference.PreferenceManager
import com.mvvm.dagger.rx.sample.data.room.UserDao
import com.mvvm.dagger.rx.sample.webservice.LoginRequest
import com.mvvm.dagger.rx.sample.webservice.LoginResponse
import com.mvvm.dagger.rx.sample.webservice.RegisterRequest
import com.mvvm.dagger.rx.sample.webservice.RegisterResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val userDao: UserDao): IUserDataSource {

    override fun isLoggedIn(context: Context): Single<Boolean> = Single.just(PreferenceManager<String>(context).findPreference(PreferenceManager.ACCESS_TOKEN,"").isNotEmpty())

    override fun getUser(context: Context): Single<User> = userDao.getUser()

    override fun saveUser(context: Context, user: User) = userDao.saveUser(user)

    override fun logout(context: Context): Completable {
        PreferenceManager<String>(context).putPreference(PreferenceManager.ACCESS_TOKEN,"")
        return Completable.complete()
    }

    override fun login(context: Context, request: LoginRequest): Single<LoginResponse> = throw UnsupportedOperationException()

    override fun register(context: Context, request: RegisterRequest): Single<RegisterResponse> = throw UnsupportedOperationException()

}