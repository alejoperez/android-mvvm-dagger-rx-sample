package com.mvvm.dagger.rx.sample.data.photos

import android.arch.lifecycle.LiveData
import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.Photo
import com.mvvm.dagger.rx.sample.data.room.PhotoDao
import com.mvvm.dagger.rx.sample.livedata.DataRequest
import com.mvvm.dagger.rx.sample.livedata.Event
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotosLocalDataSource @Inject constructor(private val photoDao: PhotoDao): IPhotosDataSource {

    override fun savePhotos(context: Context, photos: List<Photo>) {
        doAsync {
            photoDao.savePhotos(photos)
        }
    }

    override fun getPhotos(context: Context): LiveData<Event<List<Photo>>> {
        return object : DataRequest<List<Photo>>() {
            override fun dataRequestToObserve(): LiveData<List<Photo>> = photoDao.getPhotos()
        }.performRequest()
    }

}