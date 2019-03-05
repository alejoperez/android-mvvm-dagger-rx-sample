package com.mvvm.dagger.rx.sample.data.photos

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.Photo
import com.mvvm.dagger.rx.sample.data.room.PhotoDao
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosLocalDataSource @Inject constructor(private val photoDao: PhotoDao): IPhotosDataSource {

    override fun savePhotos(context: Context, photos: List<Photo>) = photoDao.savePhotos(photos)

    override fun getPhotos(context: Context): Single<List<Photo>> = photoDao.getPhotos()

}