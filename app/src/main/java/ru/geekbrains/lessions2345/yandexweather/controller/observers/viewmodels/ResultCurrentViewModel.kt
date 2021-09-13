package ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.ConstantsController
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.repository.facadeuser.RepositoryWeatherImpl
import java.lang.Thread.sleep

class ResultCurrentViewModel(
    private val liveDataToObserve: MutableLiveData<UpdateState> = MutableLiveData()): ViewModel() {

    fun getLiveData() = liveDataToObserve

    fun getDataFromRemoteSource(repositoryWeatherImpl: RepositoryWeatherImpl, mainChooserGetter: MainChooserGetter) {
        // Отправка запроса на получение погодных данных с сервера Yandex
        repositoryWeatherImpl.getWeatherFromRemoteSource(mainChooserGetter.getCurrentKnownCity()!!.lat, mainChooserGetter.getCurrentKnownCity()!!.lon, ConstantsController.ANSWER_LANGUAGE)
        // Отслеживание состояния загрузки погодных данных
        with(liveDataToObserve) {
            // Отправка сообщения О ПРОЦЕССЕ ЗАГРУЗКИ
            postValue(UpdateState.Loading)
            Thread {
                sleep(1000)
                if (mainChooserGetter.getDataWeather()?.error == null) {
                    // УСПЕШНАЯ ПЕРЕДАЧА погодных данных в основном потоке через postValue (postValue два раза подряд использовать нельзя)
                    postValue(UpdateState.Success(mainChooserGetter))
                } else {
                    // Передача СООБЩЕНИЯ ОБ ОШИБКЕ при получении погодных данных с сервера Yandex
                    postValue(UpdateState.Error(mainChooserGetter.getDataWeather()?.error))
                }
            }.start()
        }
    }
}