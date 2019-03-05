package com.mvvm.dagger.rx.sample.data.places

import android.content.Context
import com.mvvm.dagger.rx.sample.data.BaseRepositoryModule
import com.mvvm.dagger.rx.sample.data.room.Place
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class PlacesRepository
@Inject
constructor(@Named(BaseRepositoryModule.LOCAL) private val localDataSource: IPlacesDataSource,
            @Named(BaseRepositoryModule.REMOTE) private val remoteDataSource: IPlacesDataSource) : IPlacesDataSource {


    private var hasCache = false

    override fun getPlaces(context: Context): Single<List<Place>> {
        return if (hasCache) {
            localDataSource.getPlaces(context)
        } else {
            remoteDataSource.getPlaces(context)
                    .doAfterSuccess {
                        savePlaces(context,it)
                        hasCache = true
                    }
        }
    }

    override fun savePlaces(context: Context,places: List<Place>) = localDataSource.savePlaces(context, places)

    fun invalidateCache() {
        hasCache = false
    }
}