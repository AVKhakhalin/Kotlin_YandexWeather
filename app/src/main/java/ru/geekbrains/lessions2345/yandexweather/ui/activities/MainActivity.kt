package ru.geekbrains.lessions2345.yandexweather.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.ObserverDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherGetterDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModelSetter
import ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result.ResultCurrentFragment

class MainActivity: AppCompatActivity(), ResultCurrentViewModelSetter, PublisherGetterDomain,
    ObserverDomain {

    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
    private var resultCurrentViewModel: ResultCurrentViewModel = ResultCurrentViewModel()
    private val publisherDomain : PublisherDomain = PublisherDomain()
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
        resultCurrentViewModel.getDataFromRemoteSource()
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