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
    ) {
        this.dataModel = dataModel
        if (dataModel != null) {
            setFact(dataModel.fact, lat, lon, error)
        } else {
            setFact(null, lat, lon, error)
        }
    }
    private fun setFact(fact: Fact?, lat: Double, lon: Double, error: Throwable?) {
        mainChooser?.let{
            it.setFact(fact, lat, lon, error)
        }
    }
    //endregion


    // Добавление новго известного места (города) в список известных мест (городов)
    fun addKnownCities(city: City) = mainChooser?.let {mainChooser?.addKnownCities(city)}

    // Установка фильтра выбора места (города) по-умолчанию
    fun setDefaultFilterCity(defaultFilterCity: String) = mainChooser?.let {mainChooser?.setDefaultFilterCity(defaultFilterCity)}

    // Установка фильтра выбора страны по-умолчанию
    fun setDefaultFilterCountry(defaultFilterCountry: String) = mainChooser?.let {mainChooser?.setDefaultFilterCountry(defaultFilterCountry)}

    //region Методы установки позиции известного города, по которому последний раз запрошены погодные данные
    fun setPositionCurrentKnownCity(filterCity: String, filterCountry: String) = mainChooser?.let {mainChooser?.setPositionCurrentKnownCity(filterCity, filterCountry)}

    fun setPositionCurrentKnownCity(position: Int) = mainChooser?.let {mainChooser?.setPositionCurrentKnownCity(position)}
    //region

    // Установка начальных городов
    fun initKnownCities() = mainChooser?.let {mainChooser?.initKnownCities()}
}