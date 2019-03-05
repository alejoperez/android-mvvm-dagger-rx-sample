package com.mvvm.dagger.rx.sample.data.user

import android.content.Context
import com.mvvm.dagger.rx.sample.data.BaseRepositoryModule
import com.mvvm.dagger.rx.sample.data.room.User
import com.mvvm.dagger.rx.sample.data.preference.PreferenceManager
import com.mvvm.dagger.rx.sample.webservice.LoginRequest
import com.mvvm.dagger.rx.sample.webservice.LoginResponse
import com.mvvm.dagger.rx.sample.webservice.RegisterRequest
import com.mvvm.dagger.rx.sample.webservice.RegisterResponse
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class UserRepository
@Inject constructor(@Named(BaseRepositoryModule.LOCAL) private val localDataSource: IUserDataSource,
                    @Named(BaseRepositoryModule.REMOTE) private val remoteDataSource: IUserDataSource) : IUserDataSource {

    override fun saveUser(context: Context, user: User) = localDataSource.saveUser(context, user)

    override fun getUser(context: Context): Single<User> = localDataSource.getUser(context)

    override fun login(context: Context, request: LoginRequest): Single<LoginResponse> =
            remoteDataSource.login(context, request)
                    .doOnSuccess {
                        PreferenceManager<String>(context).putPreference(PreferenceManager.ACCESS_TOKEN, it.accessToken)
                        localDataSource.saveUser(context, it.toUser())
                    }

    override fun register(context: Context, request: RegisterRequest): Single<RegisterResponse> =
            remoteDataSource.register(context, request)
                    .doOnSuccess {
                        PreferenceManager<String>(context).putPreference(PreferenceManager.ACCESS_TOKEN, it.accessToken)
                        localDataSource.saveUser(context, it.toUser())
                    }


    override fun isLoggedIn(context: Context): Single<Boolean> = localDataSource.isLoggedIn(context)

    override fun logout(context: Context): Completable = localDataSource.logout(context)

}
