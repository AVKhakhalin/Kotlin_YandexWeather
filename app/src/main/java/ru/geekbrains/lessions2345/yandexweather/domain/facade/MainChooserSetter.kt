package ru.geekbrains.lessions2345.yandexweather.domain.facade

import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataModel
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.repository.facadesettings.RepositorySettingsImpl

class MainChooserSetter(mainChooser: MainChooser) {
    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private val repositorySettingsImpl: RepositorySettingsImpl = RepositorySettingsImpl()
    private var mainChooser: MainChooser? = mainChooser
    private var dataModel: DataModel? = null
    //endregion

    //region Методы для Передачи полученных данных в MainChooser
    fun setDataModel(
        dataModel: DataModel?,
        lat: Double,
        lon: Double,
        error: Throwable?
    ) { // TODO: Сделать обработку события ошибки error
        this.dataModel = dataModel
        if (dataModel != null) {
            setFact(dataModel.fact, lat, lon)
        }
    }
    fun setFact(fact: Fact?, lat: Double, lon: Double) {
        if (mainChooser != null) {
            mainChooser?.setFact(fact, lat, lon)
        }
    }
    //endregion


    // Добавление новго известного места (города) в список известных мест (городов)
    fun addKnownCities(city: City) {
        if (mainChooser != null) {
            mainChooser?.addKnownCities(city)
        }
    }

    // Установка фильтра выбора места (города) по-умолчанию
    fun setDefaultFilterCity(defaultFilterCity: String) {
        if (mainChooser != null) {
            mainChooser?.setDefaultFilterCity(defaultFilterCity)
        }
    }

    // Установка фильтра выбора страны по-умолчанию
    fun setDefaultFilterCountry(defaultFilterCountry: String) {
        if (mainChooser != null) {
            mainChooser?.setDefaultFilterCountry(defaultFilterCountry)
        }
    }

    //region Методы установки позиции известного города, по которому последний раз запрошены погодные данные
    fun setPositionCurrentKnownCity(filterCity: String, filterCountry: String) {
        if (mainChooser != null) {
            mainChooser?.setPositionCurrentKnownCity(filterCity, filterCountry)
        }
    }

    fun setPositionCurrentKnownCity(position: Int) {
        if (mainChooser != null) {
            mainChooser?.setPositionCurrentKnownCity(position)
        }
    }
    //region

    // Установка начальных городов
    fun initKnownCities() {
        if (mainChooser != null) {
            mainChooser?.initKnownCities()
        }
    }
}