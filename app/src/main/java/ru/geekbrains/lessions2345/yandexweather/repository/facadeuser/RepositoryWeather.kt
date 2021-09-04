package ru.geekbrains.lessions2345.yandexweather.repository.facadeuser

import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather

interface RepositoryWeather {
    fun getWeatherFromRemoteSource() : DataWeather
    fun getWeatherFromLocalSource() : DataWeather
}