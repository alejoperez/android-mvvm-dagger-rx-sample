package com.mvvm.dagger.rx.sample.data.user

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.User
import com.mvvm.dagger.rx.sample.webservice.LoginRequest
import com.mvvm.dagger.rx.sample.webservice.LoginResponse
import com.mvvm.dagger.rx.sample.webservice.RegisterRequest
import com.mvvm.dagger.rx.sample.webservice.RegisterResponse
import io.reactivex.Completable
import io.reactivex.Single

interface IUserDataSource {
    fun getUser(context: Context): Single<User>
    fun saveUser(context: Context,user: User)
    fun login(context: Context, request: LoginRequest): Single<LoginResponse>
    fun register(context: Context, request: RegisterRequest): Single<RegisterResponse>
    fun isLoggedIn(context: Context): Single<Boolean>
    fun logout(context: Context): Completable
}