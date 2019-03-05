package com.mvvm.dagger.rx.sample.webservice

import com.mvvm.dagger.rx.sample.data.room.Photo
import com.mvvm.dagger.rx.sample.data.room.Place
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface IApi {

    @POST("user/login")
    fun login(@Body request: LoginRequest): Single<LoginResponse>

    @POST("user/register")
    fun register(@Body request: RegisterRequest): Single<RegisterResponse>

    @GET("places")
    fun getPlaces(): Single<List<Place>>

    @GET("photos")
    fun getPhotos(): Single<List<Photo>>

}