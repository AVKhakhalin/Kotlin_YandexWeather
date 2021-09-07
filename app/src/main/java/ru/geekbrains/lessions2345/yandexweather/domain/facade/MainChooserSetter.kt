package ru.geekbrains.lessions2345.yandexweather.domain.facade

import android.content.Context
import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.repository.facadesettings.RepositorySettingsImpl
import ru.geekbrains.lessions2345.yandexweather.repository.facadeuser.RepositoryWeatherImpl

class MainChooserSetter(mainChooser: MainChooser) {
    private val repositorySettingsImpl : RepositorySettingsImpl = RepositorySettingsImpl()
//    private val repositoryWeatherImpl : RepositoryWeatherImpl = RepositoryWeatherImpl()
    private var mainChooser: MainChooser? = null

//    private var fact: Fact? = repositoryWeatherImpl.getWeatherFromRemoteSource(55.7522, 37.6156, "ru_RU")

    fun setFact() {
//        mainChooser
    }
}