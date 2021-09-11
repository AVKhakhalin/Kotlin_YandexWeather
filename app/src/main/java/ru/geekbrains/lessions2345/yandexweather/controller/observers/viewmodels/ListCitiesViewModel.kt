package ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import java.lang.Thread.sleep

class ListCitiesViewModel(
    private val liveDataToObserve: MutableLiveData<UpdateState> = MutableLiveData()) : ViewModel() {
    private var mainChooserGetter: MainChooserGetter? = null

    fun setMainChooserGetter(mainChooserGetter: MainChooserGetter) {
        this.mainChooserGetter = mainChooserGetter
    }

    fun getLiveData() = liveDataToObserve

    fun getListCities() {
        if (mainChooserGetter != null) {
//            Thread {
//                sleep(1000)
                // Передача данных в основном потоке postValue (postValue два раза подряд использовать нельзя)
                liveDataToObserve.postValue(UpdateState.ListCities(mainChooserGetter as MainChooserGetter))
//            }.start()
        }
    }
}