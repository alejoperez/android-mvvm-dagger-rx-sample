package com.mvvm.dagger.rx.sample.data.places

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.Place
import com.mvvm.dagger.rx.sample.data.room.PlaceDao
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesLocalDataSource @Inject constructor(private val placesDao: PlaceDao) : IPlacesDataSource {

    override fun savePlaces(context: Context, places: List<Place>) = placesDao.savePlaces(places)

    override fun getPlaces(context: Context): Single<List<Place>> = placesDao.getPlaces()

}