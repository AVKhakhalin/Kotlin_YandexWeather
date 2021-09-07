package ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.repository.facadeuser.RepositoryWeatherImpl
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity
import java.lang.Thread.sleep

class ResultCurrentViewModel(
    private val liveDataToObserve: MutableLiveData<Fact> = MutableLiveData()) : ViewModel() {
    /*fun getLiveData(): LiveData<Any> {
        return liveDataToObserve
    }*/
    fun getLiveData() = liveDataToObserve

    fun getDataFromRemoteSource(repositoryWeatherImpl: RepositoryWeatherImpl, mainActivity: MainActivity) {
//        var fact: Fact? = repositoryWeatherImpl.getWeatherFromRemoteSource(55.7522, 37.6156, "ru_RU")
        repositoryWeatherImpl.getWeatherFromRemoteSource(55.7522, 37.6156, "ru_RU")
        Thread {
            sleep(5000)
            // Передача данных в основном потоке postValue (postValue два раза подряд использовать нельзя)
//            liveDataToObserve.postValue(fact)
            liveDataToObserve.postValue(mainActivity.getFact())
        }.start()
    }
}