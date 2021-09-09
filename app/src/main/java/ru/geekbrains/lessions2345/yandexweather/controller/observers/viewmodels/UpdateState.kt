package ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels

import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter

class UpdateState {
    sealed class UpdateState{
        object Loading: UpdateState()
        data class Success(val mainChooserGetter: MainChooserGetter): UpdateState()
        data class Error(val error: Throwable): UpdateState()
    }
}