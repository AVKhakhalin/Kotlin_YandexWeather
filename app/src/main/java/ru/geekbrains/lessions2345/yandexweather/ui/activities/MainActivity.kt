package ru.geekbrains.lessions2345.yandexweather.ui.activities

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

        // Отображение фрагмента
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_result_weather_container, ResultCurrentFragment.newInstance()).commit()
        }
    }

    // Установка наблюдателя для обновления данных в ResultCurrentFragment
    override fun setResultCurrentViewModel(viewModel: ResultCurrentViewModel) {
        resultCurrentViewModel = viewModel
        // Получение данных
//        resultCurrentViewModel.getDataFromRemoteSource(mainChooserGetter.getFact())
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
}