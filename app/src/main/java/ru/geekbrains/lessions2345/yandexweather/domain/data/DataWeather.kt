package ru.geekbrains.lessions2345.yandexweather.domain.data

data class DataWeather(
    private val city: City = getDefaultCity(),
    private val temperature: Float = 10f, //temp	Температура (°C).	Число
    private val feelsLike: Float = 10f,   //feels_like	Ощущаемая температура (°C).	Число
    private val tempWater: Float = 9f,   // temp_water	Температура воды (°C). Параметр возвращается для населенных пунктов, где данная информация актуальна.	Число
    private val iconCode: String = "icon",   // icon	Код иконки погоды. Иконка доступна по адресу https://yastatic.net/weather/i/icons/funky/dark/<значение из поля icon>.svg.	Строка
    private val conditionCode: String = "clear", // condition	Код расшифровки погодного описания. Возможные значения:
                            //    clear — ясно.
                            //    partly-cloudy — малооблачно.
                            //    cloudy — облачно с прояснениями.
                            //    overcast — пасмурно.
                            //    drizzle — морось.
                            //    light-rain — небольшой дождь.
                            //    rain — дождь.
                            //    moderate-rain — умеренно сильный дождь.
                            //    heavy-rain — сильный дождь.
                            //    continuous-heavy-rain — длительный сильный дождь.
                            //    showers — ливень.
                            //    wet-snow — дождь со снегом.
                            //    light-snow — небольшой снег.
                            //    snow — снег.
                            //    snow-showers — снегопад.
                            //    hail — град.
                            //    thunderstorm — гроза.
                            //    thunderstorm-with-rain — дождь с грозой.
                            //    thunderstorm-with-hail — гроза с градом.	Строка
    private val windSpeed: Float = 1f,   // wind_speed	Скорость ветра (в м/с).	Число
    private val windGust: Float = 1f,    // wind_gust	Скорость порывов ветра (в м/с).	Число
    private val windDirection: String = "nw",  // wind_dir	Направление ветра. Возможные значения:
                                //    «nw» — северо-западное.
                                //    «n» — северное.
                                //    «ne» — северо-восточное.
                                //    «e» — восточное.
                                //    «se» — юго-восточное.
                                //    «s» — южное.
                                //    «sw» — юго-западное.
                                //    «w» — западное.
                                //    «с» — штиль.	Строка
    private val mmPresure: Float = 760f,       // pressure_mm	Давление (в мм рт. ст.).	Число
    private val paPressure: Float = 1013f,      // pressure_pa	Давление (в гектопаскалях).	Число
    private val humidity: Float = 70f,     // humidity	Влажность воздуха (в процентах).	Число
    private val dayTime: String = "d",     // daytime	Светлое или темное время суток. Возможные значения:
                             //    «d» — светлое время суток.
                             //    «n» — темное время суток.	Строка
    private val polar: Boolean = false,      // polar	Признак того, что время суток, указанное в поле daytime, является полярным.	Логический
    private val season: String = "summer",      // season	Время года в данном населенном пункте. Возможные значения:
                             //    «summer» — лето.
                             //    «autumn» — осень.
                             //    «winter» — зима.
                             //    «spring» — весна.	Строка
)

private fun getDefaultCity() = City("Москва", 55.0, 37.0)