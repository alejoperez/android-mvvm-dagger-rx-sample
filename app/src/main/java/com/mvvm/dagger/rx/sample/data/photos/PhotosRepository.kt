package com.mvvm.dagger.rx.sample.data.photos

import android.content.Context
import com.mvvm.dagger.rx.sample.data.BaseRepositoryModule
import com.mvvm.dagger.rx.sample.data.room.Photo
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class PhotosRepository
@Inject
constructor(@Named(BaseRepositoryModule.LOCAL) private val localDataSource: IPhotosDataSource,
            @Named(BaseRepositoryModule.REMOTE) private val  remoteDataSource: IPhotosDataSource) : IPhotosDataSource {


    private var hasCache = false

    override fun getPhotos(context: Context): Single<List<Photo>> {
        return if (hasCache) {
            localDataSource.getPhotos(context)
        } else {
            remoteDataSource.getPhotos(context)
                    .doAfterSuccess {
                        savePhotos(context,it)
                        hasCache = true
                    }

        }
    }

    override fun savePhotos(context: Context,photos: List<Photo>) = localDataSource.savePhotos(context, photos)

    fun invalidateCache() {
        hasCache = false
    }
}