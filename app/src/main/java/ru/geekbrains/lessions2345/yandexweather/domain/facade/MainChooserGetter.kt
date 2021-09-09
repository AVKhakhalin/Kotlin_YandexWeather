package ru.geekbrains.lessions2345.yandexweather.domain.facade

import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact

class MainChooserGetter(mainChooser: MainChooser) {
    var mainChooser: MainChooser = mainChooser

    fun getDataWeather(): DataWeather? {
        return mainChooser.getDataWeather()
    }

    fun getNumberKnownCites(): Int {
        return mainChooser.getNumberKnownCities()
    }

    fun getKnownCites(filterCity: String, filterCountry: String): MutableList<City>? {
        return mainChooser.getKnownCities(filterCity, filterCountry)
    }
}