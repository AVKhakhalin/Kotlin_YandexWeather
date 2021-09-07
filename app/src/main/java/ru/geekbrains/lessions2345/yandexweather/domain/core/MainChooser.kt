package ru.geekbrains.lessions2345.yandexweather.domain.core

import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserSetter

class MainChooser {
    private var dataWeather: DataWeather = DataWeather()
    private var fact: Fact? = null

    fun setDataWeather(dataWeather: DataWeather) {
        this.dataWeather = dataWeather
    }

    fun setFact(fact: Fact?) {
        this.fact = fact
    }

    fun getFact(): Fact? {
        return fact
    }
}