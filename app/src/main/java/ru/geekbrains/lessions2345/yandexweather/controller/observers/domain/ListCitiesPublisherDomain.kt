package ru.geekbrains.lessions2345.yandexweather.controller.observers.domain

class ListCitiesPublisherDomain {
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

    // Разослать событие о смене пользователем фильтра страны
    fun notifyDefaultFilterCountry(defaultFilterCountry: String) {
        if (observers != null) {
            for (observer in observers!!) {
                observer.updateFilterCountry(defaultFilterCountry)
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
}