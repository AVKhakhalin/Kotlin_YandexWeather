package ru.geekbrains.lessions2345.yandexweather.domain.facade

import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather

class MainChooserGetter(mainChooser: MainChooser) {
    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private var mainChooser: MainChooser = mainChooser
    //endregion

    // Получение данных о погоде
    fun getDataWeather(): DataWeather? {
        return mainChooser.getDataWeather()
    }

    // Получение количества известных мест (городов)
    fun getNumberKnownCites(): Int {
        return mainChooser.getNumberKnownCities()
    }

    //region МЕТОДЫ ПОЛУЧЕНИЯ СПИСКА ИЗВЕСТНЫХ МЕСТ (ГОРОДОВ)
    fun getKnownCites(filterCity: String, filterCountry: String): MutableList<City>? {
        return mainChooser.getKnownCities(filterCity, filterCountry)
    }
    fun getKnownCites(): MutableList<City>? {
        return mainChooser.getKnownCities()
    }
    //endregion

    // Получение данных об известном городе, по которому последний раз запрошены погодные данные или который выбран в списке известных городов
    fun getCurrentKnownCity(): City? {
        return mainChooser.getCurrentKnownCity()
    }

    // Получение позиции известного города, по которому последний раз запрошены погодные данные
    fun getPositionCurrentKnownCity(): Int {
        return mainChooser.getPositionCurrentKnownCity()
    }

    // Получение фильтра выбора места (города) по-умолчанию
    fun getDefaultFilterCity(): String {
        return mainChooser.getDefaultFilterCity()
    }

    // Получение фильтра выбора страны по-умолчанию
    fun getDefaultFilterCountry(): String {
        return mainChooser.getDefaultFilterCountry()
    }
}