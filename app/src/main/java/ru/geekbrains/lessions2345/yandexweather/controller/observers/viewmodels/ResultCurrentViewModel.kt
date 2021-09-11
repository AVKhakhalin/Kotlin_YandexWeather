package ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.repository.facadeuser.RepositoryWeatherImpl
import java.lang.Thread.sleep

class ResultCurrentViewModel(
    private val liveDataToObserve: MutableLiveData<DataWeather> = MutableLiveData()) : ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getDataFromRemoteSource(repositoryWeatherImpl: RepositoryWeatherImpl, mainChooserGetter : MainChooserGetter) {
        repositoryWeatherImpl.getWeatherFromRemoteSource(mainChooserGetter.getCurrentKnownCity()!!.lat, mainChooserGetter.getCurrentKnownCity()!!.lon, "ru_RU")
        Thread {
            sleep(1000)
            // Передача данных в основном потоке postValue (postValue два раза подряд использовать нельзя)
            liveDataToObserve.postValue(mainChooserGetter.getDataWeather())
        }.start()
    }
}