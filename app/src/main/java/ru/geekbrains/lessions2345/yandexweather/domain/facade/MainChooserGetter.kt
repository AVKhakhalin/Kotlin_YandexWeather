package ru.geekbrains.lessions2345.yandexweather.domain.facade

import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact

class MainChooserGetter(mainChooser: MainChooser) {
    val dataWeather: DataWeather = DataWeather()
    var mainChooser: MainChooser = mainChooser

    fun getFact(): Fact? {
        return mainChooser.getFact()
    }
}