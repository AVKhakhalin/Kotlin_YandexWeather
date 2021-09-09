package ru.geekbrains.lessions2345.yandexweather.domain.core

import android.widget.Toast
import ru.geekbrains.lessions2345.yandexweather.domain.ConstantsDomain
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataSettings
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserSetter
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity

class MainChooser() {
    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
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
    private var positionCurrentKnownCity: Int = -1
    private var fact: Fact? = null
    //endregion

    // Получение позиции известного города, по которому последний раз запрошены погодные данные
    fun getPositionCurrentKnownCity(): Int {
        return positionCurrentKnownCity
    }

    //region Методы установки позиции известного города, по которому последний раз запрошены погодные данные
    fun setPositionCurrentKnownCity(filterCity: String, filterCountry: String) {
        if (knownCities != null) {
            knownCities?.forEachIndexed() { position, city ->
                if ((city.country.equals(filterCountry) == true) && (city.name.equals(filterCity) == true)) {
                    positionCurrentKnownCity = position
                    return
                }
            }
        }
    }
    fun setPositionCurrentKnownCity(position: Int) {
        positionCurrentKnownCity = position
    }
    //endregion


        //region МЕТОДЫ ДЛЯ ПОЛУЧЕНИЯ СПИСКА ИЗВЕСТНЫХ ГОРОДОВ
    fun getKnownCities(filterCity: String, filterCountry: String): MutableList<City>? {
        var newKnownCities: MutableList<City>? = null
        if (filterCountry.equals("") == true) {
            // Фильтрация по названию города
            knownCities?.forEach { city ->
                if ((filterCity.equals("") == true) || (city.name.equals(filterCity) == true) || (city.name.indexOf(filterCity) > -1)) {
                    if (newKnownCities == null) {
                        newKnownCities = mutableListOf(city)
                    } else {
                        newKnownCities?.add(city)
                    }
                }
            }
            return knownCities
        } else {
            if (knownCities != null) {
                knownCities?.forEach { city ->
                    if ((city.country.equals(filterCountry) == true) && ((filterCity.equals("") == true) || (city.name.equals(filterCity) == true) || (city.name.indexOf(filterCity) > -1))) {
                        if (newKnownCities == null) {
                            newKnownCities = mutableListOf(city)
                        } else {
                            newKnownCities?.add(city)
                        }
                    }
                }
                return newKnownCities
            } else {
                return null
            }
        }
    }
    //endregion

    // Добавить новый город в список известных городов
    fun addKnownCities(city: City) {
        if (knownCities == null) {
            knownCities = mutableListOf(city)
        } else {
            knownCities?.add(city)
        }
    }

    // Получить количество известных городов
    fun getNumberKnownCities(): Int {
        if (knownCities == null) {
            return 0
        } else {
            return knownCities!!.size
        }
    }

    // Получить данные о погоде сейчас
    fun getDataWeather(): DataWeather? {
        if (dataWeather == null) {
            return null
        } else {
            return dataWeather
        }
    }

    // Установить фактические данные о погоде
    fun setFact(fact: Fact?, lat: Double, lon: Double) {
        this.fact = fact
        if ((fact != null) && (dataWeather != null)) {
            dataWeather?.city = City(ConstantsDomain.UNKNOWN_TEXT, lat, lon, ConstantsDomain.UNKNOWN_TEXT)
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