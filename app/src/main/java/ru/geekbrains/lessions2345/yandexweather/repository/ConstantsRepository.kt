package ru.geekbrains.lessions2345.yandexweather.repository

class ConstantsRepository {
    companion object {
        // Константы для получения данных о погоде
        @JvmField
        val baseUrl : String = "https://api.weather.yandex.ru/"
        @JvmField
        val endPoint : String = "v2/informers"
        @JvmField
        val yandexKeyName : String = "X-Yandex-API-Key"
        @JvmField
        val yandexKeyValue : String = "ebbee072-d212-420e-9f62-4d716b0499e9"
        @JvmField
        val latName : String = "lat"
        @JvmField
        val lonName : String = "lon"
        @JvmField
        val langName : String = "lang"

        // Константы для локальных настроек программы
    }
}