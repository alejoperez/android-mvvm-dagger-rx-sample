package com.mvvm.dagger.rx.sample.data.places

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.Place
import com.mvvm.dagger.rx.sample.webservice.IApi
import io.reactivex.Single
import java.lang.UnsupportedOperationException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlacesRemoteDataSource @Inject constructor(private val api: IApi)  : IPlacesDataSource {

    override fun savePlaces(context: Context, places: List<Place>) = throw UnsupportedOperationException()

    override fun getPlaces(context: Context): Single<List<Place>> = api.getPlaces()

}