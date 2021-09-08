package ru.geekbrains.lessions2345.yandexweather.domain.core

import android.widget.Toast
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataSettings
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserSetter
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity

class MainChooser() {
    private var dataWeather: DataWeather? = DataWeather()
    private var dataSettings: DataSettings? = null

    private var fact: Fact? = null

    fun getDataWeather() : DataWeather? {
        if (dataWeather == null) {
            return null
        } else {
            return dataWeather
        }
    }

    fun setFact(fact: Fact?, lan: Double, lon: Double) {
        this.fact = fact
        if ((fact != null) && (dataWeather != null)) {
            dataWeather?.city = City("Unknown", lan, lon)
            dataWeather?.temperature = fact.temp
            dataWeather?.feelsLike = fact.feels_like
            dataWeather?.tempWater = fact.temp_water
            dataWeather?.iconCode = fact.icon
            dataWeather?.conditionCode = fact.condition
            dataWeather?.windSpeed = fact.wind_speed
            dataWeather?.windGust = fact.wind_gust
            dataWeather?.windDirection = fact.wind_dir
            dataWeather?.mmPresure = fact.pressure_mm
            dataWeather?.paPressure = fact.pressure_pa
            dataWeather?.humidity = fact.humidity
            dataWeather?.dayTime = fact.daytime
            dataWeather?.polar = fact.polar
            dataWeather?.season = fact.season
        }
    }
}