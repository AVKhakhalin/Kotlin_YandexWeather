package ru.geekbrains.lesson_1423_2_2_main.view

import ru.geekbrains.lessions2345.yandexweather.domain.data.City

interface OnItemViewClickListener {
    fun onItemClick(city: City)
}