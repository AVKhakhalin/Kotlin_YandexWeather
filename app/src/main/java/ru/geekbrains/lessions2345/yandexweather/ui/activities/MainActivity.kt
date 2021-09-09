package ru.geekbrains.lessions2345.yandexweather.ui.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.controller.ConstantsController
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.ObserverDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherGetterDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModelSetter
import ru.geekbrains.lessions2345.yandexweather.domain.ConstantsDomain
import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserSetter
import ru.geekbrains.lessions2345.yandexweather.repository.ConstantsRepository
import ru.geekbrains.lessions2345.yandexweather.repository.facadeuser.RepositoryWeatherImpl
import ru.geekbrains.lessions2345.yandexweather.ui.ConstantsUi
import ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result.ResultCurrentFragment

class MainActivity: AppCompatActivity(), ResultCurrentViewModelSetter, PublisherGetterDomain, ObserverDomain {

    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private var resultCurrentViewModel: ResultCurrentViewModel = ResultCurrentViewModel()
    private val publisherDomain : PublisherDomain = PublisherDomain()
    private val mainChooser : MainChooser = MainChooser()
    private val mainChooserSetter : MainChooserSetter = MainChooserSetter(mainChooser)
    private val mainChooserGetter : MainChooserGetter = MainChooserGetter(mainChooser)
    private val repositoryWeatherImpl: RepositoryWeatherImpl = RepositoryWeatherImpl(mainChooserSetter)
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Подключение наблюдателя за domain к MainActivity
        publisherDomain.subscribe(this)

        // Случай первого запуска активити
        if (savedInstanceState == null) {
            // Получение известных городов
            getKnownCities()
            // Отображение фрагмента с данными о погоде в выбранном месте (city)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_result_weather_container, ResultCurrentFragment.newInstance()).commit()
        }
    }

    // Установка наблюдателя для обновления данных в ResultCurrentFragment
    override fun setResultCurrentViewModel(viewModel: ResultCurrentViewModel) {
        resultCurrentViewModel = viewModel
        // Получение данных
        resultCurrentViewModel.getDataFromRemoteSource(repositoryWeatherImpl, mainChooserGetter)
    }

    // Установка наблюдателя для domain
    override fun updateUserChoose() {
        TODO("Not yet implemented")
    }

    // Создание метода для передачи наблюдателя для domain во фрагменты
    override fun getPublisherDomain(): PublisherDomain {
        return publisherDomain
    }

    override fun onDestroy() {
        super.onDestroy()
        // Сохранение известных городов
        saveKnownCities()
    }

    //region МЕТОДЫ ДЛЯ РАБОТЫ С SHAREDPREFERENCES
    // Сохранение настроек в SharedPreferences
    private fun saveKnownCities() {
        val numberKnownCities = mainChooserGetter.getNumberKnownCites()
        val positionCurrentKnownCity = mainChooserGetter.getPositionCurrentKnownCity()
        val sharedPreferences: SharedPreferences = getSharedPreferences(ConstantsUi.SHARED_SAVE, MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(ConstantsUi.SHARED_NUMBER_SAVED_CITIES, numberKnownCities)
        editor.putInt(ConstantsUi.SHARED_POSITION_CURRENT_KNOWN_CITY, positionCurrentKnownCity)
        if (numberKnownCities > 0) {
            val nameStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "name$i"}
            val latStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "lat$i"}
            val lonStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "lone$i"}
            val countryStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "country$i"}
            val knownCities: List<City>? = mainChooserGetter.getKnownCites("","")
            if (knownCities != null) {
                knownCities.forEachIndexed { index, element ->
                    editor.putString(nameStringArray[index], element.name)
                    editor.putFloat(latStringArray[index], element.lat as Float)
                    editor.putFloat(lonStringArray[index], element.lon as Float)
                    editor.putString(countryStringArray[index], element.country)
                }
            }
        }
        editor.apply()
    }

    // Получение настроек из SharedPreferences
    private fun getKnownCities() {
        val sharedPreferences: SharedPreferences = getSharedPreferences(ConstantsUi.SHARED_SAVE, MODE_PRIVATE)
        val numberKnownCities = sharedPreferences.getInt(ConstantsUi.SHARED_NUMBER_SAVED_CITIES, 0)
        if (numberKnownCities > 0) {
            val nameStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "name$i"}
            val latStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "lat$i"}
            val lonStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "lone$i"}
            val countryStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "country$i"}
            repeat(numberKnownCities) {
                mainChooserSetter.addKnownCities(City(
                    sharedPreferences.getString(nameStringArray[it], ConstantsUi.UNKNOWN_TEXT)!!,
                    sharedPreferences.getFloat(latStringArray[it], ConstantsUi.ZERO_FLOAT) as Double,
                    sharedPreferences.getFloat(lonStringArray[it], ConstantsUi.ZERO_FLOAT) as Double,
                    sharedPreferences.getString(countryStringArray[it], ConstantsUi.UNKNOWN_TEXT)!!))
            }
        }
    }
    //endregion
}