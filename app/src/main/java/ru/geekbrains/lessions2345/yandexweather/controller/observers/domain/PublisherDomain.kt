package ru.geekbrains.lessions2345.yandexweather.controller.observers.domain

import ru.geekbrains.lessions2345.yandexweather.domain.data.City

class PublisherDomain {
    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private var observers: MutableList<ObserverDomain>? = null
    //endregion

    // Подписаться на паблишер
    fun subscribe(observer: ObserverDomain) {
        if (observers == null) {
            observers = MutableList<ObserverDomain>(1) { observer }
        } else {
            observers?.add(observer)
        }
    }

    // Отписаться от паблишера
    fun unsubscribe(observer: ObserverDomain) {
        observers?.remove(observer)
    }

    // Разослать событие о действиях пользователя
    fun notifyCity(city: City) {
        if (observers != null) {
            for (observer in observers!!) {
                observer.updateCity(city)
            }
        }
    }

    // Разослать событие о смене пользователем фильтра страны
    fun notifyDefaultFilterCity(defaultFilterCity: String) {
        if (observers != null) {
            for (observer in observers!!) {
                observer.updateFilterCity(defaultFilterCity)
            }
        }
    }

    // Разослать событие о смене позиции текущего известного места (города)
    fun notifyPositionCurrentKnownCity(positionCurrentKnownCity: Int) {
        if (observers != null) {
            for (observer in observers!!) {
                observer.updatePositionCurrentKnownCity(positionCurrentKnownCity)
            }
        }
    }


}