package ru.geekbrains.lessions2345.yandexweather.controller.observers.domain

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
    fun notifySingle() {
        if (observers != null) {
            for (observer in observers!!) {
                // Передать что-то
            }
        }
    }
}