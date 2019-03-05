package com.mvvm.dagger.rx.sample.places

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.room.Place
import com.mvvm.dagger.rx.sample.data.places.PlacesRepository
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.utils.addTo
import com.mvvm.dagger.rx.sample.utils.applyIoAndMainThreads
import com.mvvm.dagger.rx.sample.utils.getEventError
import javax.inject.Inject

class PlacesViewModel @Inject constructor(application: Application,private val placesRepository: PlacesRepository): BaseViewModel(application) {

    val places = MutableLiveData<Event<List<Place>>>()

    fun getPlaces() {
        placesRepository.getPlaces(getApplication())
                .applyIoAndMainThreads()
                .subscribe(
                        {
                            places.value = Event.success(it)
                        },
                        {
                            places.value = it.getEventError()
                        }
                )
                .addTo(compositeDisposable)
    }

}