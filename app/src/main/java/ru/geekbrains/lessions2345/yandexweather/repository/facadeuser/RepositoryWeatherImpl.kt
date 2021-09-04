package ru.geekbrains.lessions2345.yandexweather.repository.facadeuser

import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather

class RepositoryWeatherImpl : RepositoryWeather {
    override fun getWeatherFromRemoteSource(): DataWeather {
        return DataWeather()
    }

    override fun getWeatherFromLocalSource(): DataWeather {
        return DataWeather()
    }

}