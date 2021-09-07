package ru.geekbrains.lessions2345.yandexweather.repository.facadeuser

import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity

interface RepositoryWeather {
//    fun getWeatherFromRemoteSource(lat: Double, lon: Double, lang: String) : Fact?
    fun getWeatherFromRemoteSource(lat: Double, lon: Double, lang: String)
    fun getWeatherFromLocalSource() : DataWeather
}