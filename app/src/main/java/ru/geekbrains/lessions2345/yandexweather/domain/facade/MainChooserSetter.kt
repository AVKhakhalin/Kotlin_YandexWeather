package ru.geekbrains.lessions2345.yandexweather.domain.facade

import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataModel
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.repository.facadesettings.RepositorySettingsImpl

class MainChooserSetter(mainChooser: MainChooser) {
    private val repositorySettingsImpl : RepositorySettingsImpl = RepositorySettingsImpl()
    private var mainChooser: MainChooser? = mainChooser
    private var dataModel: DataModel? = null

    fun setFact(fact: Fact?, lan: Double, lon: Double) {
        if (mainChooser != null) {
            mainChooser?.setFact(fact, lan, lon)
        }
    }

    fun setDataModel(dataModel: DataModel?, lan: Double, lon: Double, error: Throwable?) {
        this.dataModel = dataModel
        if (dataModel != null) {
            setFact(dataModel?.fact, lan, lon)
        }
    }

    fun addKnownCities(city: City) {
        if (mainChooser != null) {
            mainChooser?.addKnownCities(city)
        }
    }
}