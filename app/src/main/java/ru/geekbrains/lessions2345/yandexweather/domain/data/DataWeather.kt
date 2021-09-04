package ru.geekbrains.lessions2345.yandexweather.domain.data

data class DataWeather(
    val city: City = getDefaultCity(),
    val temperature: Float, //temp	Температура (°C).	Число
    val feelsLike: Float,   //feels_like	Ощущаемая температура (°C).	Число
    val tempWater: Float,   // temp_water	Температура воды (°C). Параметр возвращается для населенных пунктов, где данная информация актуальна.	Число
    val iconCode: String,   // icon	Код иконки погоды. Иконка доступна по адресу https://yastatic.net/weather/i/icons/funky/dark/<значение из поля icon>.svg.	Строка
    val conditionCode: Int, // condition	Код расшифровки погодного описания. Возможные значения:
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
    val windSpeed: Float,   // wind_speed	Скорость ветра (в м/с).	Число
    val windGust: Float,    // wind_gust	Скорость порывов ветра (в м/с).	Число
    val windDirection: String,  // wind_dir	Направление ветра. Возможные значения:
                                //    «nw» — северо-западное.
                                //    «n» — северное.
                                //    «ne» — северо-восточное.
                                //    «e» — восточное.
                                //    «se» — юго-восточное.
                                //    «s» — южное.
                                //    «sw» — юго-западное.
                                //    «w» — западное.
                                //    «с» — штиль.	Строка
    val mmPresure: Float,       // pressure_mm	Давление (в мм рт. ст.).	Число
    val paPressure: Float,      // pressure_pa	Давление (в гектопаскалях).	Число
    val humidity: Float,     // humidity	Влажность воздуха (в процентах).	Число
    val dayTime: String,     // daytime	Светлое или темное время суток. Возможные значения:
                             //    «d» — светлое время суток.
                             //    «n» — темное время суток.	Строка
    val polar: Boolean,      // polar	Признак того, что время суток, указанное в поле daytime, является полярным.	Логический
    val season: String,      // season	Время года в данном населенном пункте. Возможные значения:
                             //    «summer» — лето.
                             //    «autumn» — осень.
                             //    «winter» — зима.
                             //    «spring» — весна.	Строка
)

private fun getDefaultCity() = City("Москва", 55.0, 37.0)