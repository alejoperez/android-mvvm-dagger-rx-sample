package com.mvvm.dagger.rx.sample.data.photos

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.Photo
import com.mvvm.dagger.rx.sample.webservice.IApi
import io.reactivex.Single
import java.lang.UnsupportedOperationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosRemoteDataSource @Inject constructor(private val api: IApi) : IPhotosDataSource {

    override fun savePhotos(context: Context, photos: List<Photo>) = throw UnsupportedOperationException()

    override fun getPhotos(context: Context): Single<List<Photo>> = api.getPhotos()

}