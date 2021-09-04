package ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class ResultCurrentViewModel(private val liveDataToObserve: MutableLiveData<Any> = MutableLiveData()) : ViewModel() {
    /*fun getLiveData(): LiveData<Any> {
        return liveDataToObserve
    }*/
    fun getLiveData() = liveDataToObserve

    fun getDataFromRemoteSource() {
        Thread {
            sleep(2000)
            // Передача данных в основном потоке postValue (postValue два раза подряд использовать нельзя)
            liveDataToObserve.postValue(Any())
        }.start()
    }
}