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
    private var knownCities: MutableList<City>? = mutableListOf(
        City("Москва", 55.755826, 37.617299900000035, "Россия"),
        City("Санкт-Петербург", 59.9342802, 30.335098600000038, "Россия"),
        City("Новосибирск", 55.00835259999999, 82.93573270000002, "Россия"),
        City("Екатеринбург", 56.83892609999999, 60.60570250000001, "Россия"),
        City("Нижний Новгород", 56.2965039, 43.936059, "Россия"),
        City("Казань", 55.8304307, 49.06608060000008, "Россия"),
        City("Челябинск", 55.1644419, 61.4368432, "Россия"),
        City("Омск", 54.9884804, 73.32423610000001, "Россия"),
        City("Ростов-на-Дону", 47.2357137, 39.701505, "Россия"),
        City("Уфа", 54.7387621, 55.972055400000045, "Россия"),
        City("Лондон", 51.5085300, -0.1257400, "Великобритания"),
        City("Токио", 35.6895000, 139.6917100, "Япония"),
        City("Париж", 48.8534100, 2.3488000, "Франция"),
        City("Берлин", 52.52000659999999, 13.404953999999975, "Германия"),
        City("Рим", 41.9027835, 12.496365500000024, "Италия"),
        City("Минск", 53.90453979999999, 27.561524400000053,"Белоруссия"),
        City("Стамбул", 41.0082376, 28.97835889999999, "Турция"),
        City("Вашингтон", 38.9071923, -77.03687070000001, "США"),
        City("Киев", 50.4501, 30.523400000000038, "Украина"),
        City("Пекин", 39.90419989999999, 116.40739630000007, "Китай")
    )
    private var fact: Fact? = null

    fun getKnownCities(): MutableList<City>? {
        return knownCities
    }

    fun addKnownCities(city: City) {
        if (knownCities == null) {
            knownCities = MutableList<City>(1){city}
        } else {
            knownCities?.add(city)
        }
    }

    fun getNumberKnownCities(): Int {
        if (knownCities == null) {
            return 0
        } else {
            return knownCities!!.size
        }
    }

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
            dataWeather?.city = City("Unknown", lan, lon, "Unknown")
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