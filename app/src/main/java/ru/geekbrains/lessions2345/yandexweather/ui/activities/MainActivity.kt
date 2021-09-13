package ru.geekbrains.lessions2345.yandexweather.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.*
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ListCitiesViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ListCitiesViewModelSetter
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModelSetter
import ru.geekbrains.lessions2345.yandexweather.domain.core.MainChooser
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserGetter
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserSetter
import ru.geekbrains.lessions2345.yandexweather.repository.facadeuser.RepositoryWeatherImpl
import ru.geekbrains.lessions2345.yandexweather.ui.ConstantsUi
import ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result.ResultCurrentFragment
import ru.geekbrains.lesson_1423_2_2_main.view.main.ListCitiesFragment

class MainActivity:
    AppCompatActivity(),
    ResultCurrentViewModelSetter,
    ListCitiesViewModelSetter,
    PublisherGetterDomain,
    ListSitiesPublisherGetterDomain,
    ObserverDomain {

    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private var resultCurrentViewModel: ResultCurrentViewModel = ResultCurrentViewModel()
    private var listCitiesViewModel: ListCitiesViewModel = ListCitiesViewModel()
    private val publisherDomain : PublisherDomain = PublisherDomain()
    private val listCitiesPublisherDomain : ListCitiesPublisherDomain = ListCitiesPublisherDomain()
    private val mainChooser : MainChooser = MainChooser()
    private val mainChooserSetter : MainChooserSetter = MainChooserSetter(mainChooser)
    private val mainChooserGetter : MainChooserGetter = MainChooserGetter(mainChooser)
    private val repositoryWeatherImpl: RepositoryWeatherImpl = RepositoryWeatherImpl(mainChooserSetter)
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Подключение наблюдателей за domain к MainActivity
        publisherDomain.subscribe(this)
        listCitiesPublisherDomain.subscribe(this)

        // Случай первого запуска активити
        if (savedInstanceState == null) {
            // Получение известных городов
            getKnownCities()
            // Выбор
            if (mainChooserGetter.getPositionCurrentKnownCity() == -1) {
                // Отображение фрагмента со списком мест (city) для выбора интересующего места
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_result_weather_container, ListCitiesFragment.newInstance(mainChooserGetter.getDefaultFilterCountry().equals("Россия") == true))
                    .commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_result_weather_container, ResultCurrentFragment.newInstance(mainChooserGetter.getCurrentKnownCity()!!))
                    .commit()
            }
        }
    }

    // Установка наблюдателя для обновления данных в ResultCurrentFragment
    override fun setResultCurrentViewModel(viewModel: ResultCurrentViewModel) {
        resultCurrentViewModel = viewModel
    }

    // Установка наблюдателя для обновления данных в ListCitiesFragment
    override fun setListCitiesViewModel(viewModel: ListCitiesViewModel) {
        listCitiesViewModel = viewModel
        listCitiesViewModel.setMainChooserGetter(mainChooserGetter)
    }

    override fun onStop() {
        super.onStop()
        // Сохранение известных городов
        saveKnownCities()
    }

    //region МЕТОДЫ ДЛЯ РАБОТЫ С SHAREDPREFERENCES
    // Сохранение настроек в SharedPreferences
    private fun saveKnownCities() {
        val numberKnownCities = mainChooserGetter.getNumberKnownCites()
        val sharedPreferences: SharedPreferences = getSharedPreferences(ConstantsUi.SHARED_SAVE, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(ConstantsUi.SHARED_NUMBER_SAVED_CITIES, numberKnownCities)
        if (numberKnownCities > 0) {
            val nameStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "name$i"}
            val latStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "lat$i"}
            val lonStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "lone$i"}
            val countryStringArray: Array<String> = Array<String>(numberKnownCities) { i -> "country$i"}
            val knownCities: List<City>? = mainChooserGetter.getKnownCites("","")
            if (knownCities != null) {
                knownCities.forEachIndexed { index, element ->
                    editor.putString(nameStringArray[index], element.name)
                    editor.putFloat(latStringArray[index], element.lat.toFloat())
                    editor.putFloat(lonStringArray[index], element.lon.toFloat())
                    editor.putString(countryStringArray[index], element.country)
                }
            }
        }
        editor.putInt(ConstantsUi.SHARED_POSITION_CURRENT_KNOWN_CITY, mainChooserGetter.getPositionCurrentKnownCity())
        editor.putString(ConstantsUi.SHARED_DEFAULT_FILTER_CITY, mainChooserGetter.getDefaultFilterCity())
        editor.putString(ConstantsUi.SHARED_DEFAULT_FILTER_COUNTRY, mainChooserGetter.getDefaultFilterCountry())
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
                    sharedPreferences.getFloat(latStringArray[it], ConstantsUi.ZERO_FLOAT).toDouble(),
                    sharedPreferences.getFloat(lonStringArray[it], ConstantsUi.ZERO_FLOAT).toDouble(),
                    sharedPreferences.getString(countryStringArray[it], ConstantsUi.UNKNOWN_TEXT)!!))
            }
        }
        mainChooserSetter.setPositionCurrentKnownCity(sharedPreferences.getInt(ConstantsUi.SHARED_POSITION_CURRENT_KNOWN_CITY, -1))
        mainChooserSetter.setDefaultFilterCity(sharedPreferences.getString(ConstantsUi.SHARED_DEFAULT_FILTER_CITY, "")!!)
        mainChooserSetter.setDefaultFilterCountry(sharedPreferences.getString(ConstantsUi.SHARED_DEFAULT_FILTER_COUNTRY, "")!!)

        // Установка известных городов по-умолчанию
        if (mainChooserGetter.getNumberKnownCites() == 0) {
            mainChooserSetter.initKnownCities()
        }
    }
    //endregion

    //region МЕТОДЫ ДЛЯ ПЕРЕДАЧИ РЕЗУЛЬТАТОВ ДЕЙСТВИЙ ПОЛЬЗОВАТЕЛЯ ВО ФРАГМЕНТАХ В КЛАСС MainChooser (domain)
    // Создание метода для передачи наблюдателя PublisherDomain для domain во фрагменты
    override fun getPublisherDomain(): PublisherDomain {
        return publisherDomain
    }
    // Создание метода для передачи наблюдателя ListCitiesPublisherDomain для domain во фрагменты
    override fun getListSitiesPublisherDomain(): ListCitiesPublisherDomain {
        return listCitiesPublisherDomain
    }
    override fun updateFilterCountry(filterCountry: String) {
        mainChooserSetter.setDefaultFilterCountry(filterCountry)
    }
    override fun updateFilterCity(filterCity: String) {
        mainChooserSetter.setDefaultFilterCity(filterCity)
    }
    override fun updatePositionCurrentKnownCity(positionCurrentKnownCity: Int) {
        mainChooserSetter.setPositionCurrentKnownCity(positionCurrentKnownCity)
    }

    // Установка наблюдателя для domain
    override fun updateCity(city: City) {
        mainChooserSetter.setPositionCurrentKnownCity(city.name, city.country)
        // Получение данных в resultCurrentViewModel
        resultCurrentViewModel.getDataFromRemoteSource(repositoryWeatherImpl, mainChooserGetter)
    }
    //endregion
}