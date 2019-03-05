package com.mvvm.dagger.rx.sample.data.places

import android.content.Context
import com.mvvm.dagger.rx.sample.data.room.Place
import io.reactivex.Single

interface IPlacesDataSource {

    fun getPlaces(context: Context): Single<List<Place>>

    fun savePlaces(context: Context, places: List<Place>)
}