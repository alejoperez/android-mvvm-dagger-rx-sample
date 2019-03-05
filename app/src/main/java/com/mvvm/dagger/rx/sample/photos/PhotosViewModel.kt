package com.mvvm.dagger.rx.sample.photos

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import com.mvvm.dagger.rx.sample.base.BaseViewModel
import com.mvvm.dagger.rx.sample.data.room.Photo
import com.mvvm.dagger.rx.sample.data.photos.PhotosRepository
import com.mvvm.dagger.rx.sample.livedata.Event
import com.mvvm.dagger.rx.sample.utils.addTo
import com.mvvm.dagger.rx.sample.utils.applyIoAndMainThreads
import com.mvvm.dagger.rx.sample.utils.getEventError
import javax.inject.Inject

class PhotosViewModel @Inject constructor(application: Application, private val photosRepository: PhotosRepository): BaseViewModel(application) {

    val isLoading = ObservableBoolean(false)

    val photos = MutableLiveData<Event<List<Photo>>>()

    fun getPhotos() {
        photosRepository.getPhotos(getApplication())
                .applyIoAndMainThreads()
                .doOnSubscribe { showProgress() }
                .doAfterTerminate { hideProgress() }
                .subscribe(
                        {
                            photos.value = Event.success(it)
                        },
                        {
                            photos.value = it.getEventError()
                        })
                .addTo(compositeDisposable)
    }

    private fun showProgress() = isLoading.set(true)

    private fun hideProgress() = isLoading.set(false)

}