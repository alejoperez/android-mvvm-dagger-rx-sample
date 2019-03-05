package com.mvvm.dagger.rx.sample.data.user

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.User
import com.mvvm.dagger.rx.sample.webservice.*
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(private val api: IApi): IUserDataSource {

    override fun login(context: Context, request: LoginRequest): Single<LoginResponse> = api.login(request)

    override fun register(context: Context, request: RegisterRequest): Single<RegisterResponse> = api.register(request)

    override fun logout(context: Context) = throw UnsupportedOperationException()

    override fun getUser(context: Context): Single<User> = throw UnsupportedOperationException()

    override fun saveUser(context: Context, user: User) = throw UnsupportedOperationException()

    override fun isLoggedIn(context: Context): Single<Boolean> = throw UnsupportedOperationException()
}