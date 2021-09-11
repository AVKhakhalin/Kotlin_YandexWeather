package ru.geekbrains.lessions2345.yandexweather.controller.observers.domain

import ru.geekbrains.lessions2345.yandexweather.domain.data.City

interface ObserverDomain {
    fun updateFilterCountry(filterCountry: String)
    fun updateFilterCity(filterCity: String)
    fun updateCity(city: City)
    fun updatePositionCurrentKnownCity(positionCurrentKnownCity: Int)
}