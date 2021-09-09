package ru.geekbrains.lessions2345.yandexweather.domain.facade

import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather

class MainChooserGetter(mainChooser: MainChooser) {
    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    var mainChooser: MainChooser = mainChooser
    //endregion

    // Получение данных о погоде
    fun getDataWeather(): DataWeather? {
        return mainChooser.getDataWeather()
    }

    // Получение количества известных мест (городов)
    fun getNumberKnownCites(): Int {
        return mainChooser.getNumberKnownCities()
    }

    // Получение списка известных мест (городов)
    fun getKnownCites(filterCity: String, filterCountry: String): MutableList<City>? {
        return mainChooser.getKnownCities(filterCity, filterCountry)
    }

    // Получение позиции известного города, по которому последний раз запрошены погодные данные
    fun getPositionCurrentKnownCity(): Int {
        return mainChooser.getPositionCurrentKnownCity()
    }
}