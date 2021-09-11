package ru.geekbrains.lessions2345.yandexweather.controller.observers.domain

interface ObserverDomain {
    fun updateFilterCountry(filterCountry: String)
    fun updateUserChoose() // TODO: добавить данные для метода обновления в domain

}