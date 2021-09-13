package ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModelSetter
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.UpdateState
import ru.geekbrains.lessions2345.yandexweather.databinding.FragmentResultCurrentBinding
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity
import ru.geekbrains.lesson_1423_2_2_main.view.main.ListCitiesFragment

class ResultCurrentFragment(//region ЗАДАНИЕ ПЕРЕМЕННЫХ
    // Данные о месте (городе)
    private var city: City
) : Fragment() {
    // Фабричный метод создания фрагмента
    companion object {
        fun newInstance(city: City) = ResultCurrentFragment(city)
    }

    // Ссылка на ResultCurrentViewModel
    private lateinit var resultCurrentViewModel: ResultCurrentViewModel

    // Создание binding с возможностью удаления (имя класса FragmentResultCurrentBinding формируется из класса ResultCurrentFragment)
    // Класс FragmentResultCurrentBinding - представление макета fragment_result_current.xml в виде кода
    private var bindingReal: FragmentResultCurrentBinding? = null
    private val bindingNotReal: FragmentResultCurrentBinding
        get() {
            return bindingReal!!
        }

    // Создание наблюдателя в domain
    var publisherDomain : PublisherDomain = PublisherDomain()
    //endregion

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Создание viewModel
        resultCurrentViewModel = ViewModelProvider(this).get(ResultCurrentViewModel::class.java)
        // Задание наблюдателя для данного фрагмента (viewModel)
        (context as ResultCurrentViewModelSetter).setResultCurrentViewModel(resultCurrentViewModel)
        // Получение наблюдателя для domain
        publisherDomain = (context as MainActivity).getPublisherDomain()
        // Установка выбранного места (города) как текущего известного места (города). Теперь при обращении к классу MainChooser он будет выбираться во всех запросах
        publisherDomain.notifyCity(city)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingReal = FragmentResultCurrentBinding.inflate(inflater, container, false)
        return bindingNotReal.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Создание observer во vieModel
        resultCurrentViewModel.getLiveData().observe(viewLifecycleOwner, Observer<UpdateState> {updateState: UpdateState ->
           renderData(updateState)
        })
        bindingReal?.resultCurrentConstraintLayout?.setOnClickListener(View.OnClickListener {
            // Сброс фильтра места (города)
            with(publisherDomain) {
                notifyDefaultFilterCity("")
                notifyPositionCurrentKnownCity(-1)
            }
            // Отображение фрагмента со списком мест (city) для выбора погоды по другому интересующему месту
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_result_weather_container, ListCitiesFragment.newInstance(
                    if (city.country.equals("Россия") == true) true else false))
                .commit()
        })
    }

    private fun renderData(updateState: UpdateState) {
        when (updateState) {
            is UpdateState.Success -> {
                bindingReal?.resultCurrentConstraintLayoutLoadingLayout?.visibility = View.GONE
                bindingReal?.let{
                    it.resultCurrentConstraintLayoutCityName?.text = updateState.mainChooserGetter.getDataWeather()?.city?.name
                    it.resultCurrentConstraintLayoutCityCoordinates?.text = "${updateState.mainChooserGetter.getDataWeather()?.city?.lat}; ${updateState.mainChooserGetter.getDataWeather()?.city?.lon}"
                    it.resultCurrentConstraintLayoutTemperatureValue?.text = "${updateState.mainChooserGetter.getDataWeather()?.temperature}"
                    it.resultCurrentConstraintLayoutFeelslikeValue?.text = "${updateState.mainChooserGetter.getDataWeather()?.feelsLike}"
                }
                Snackbar.make(bindingReal?.root!!, "Данные УСПЕШНО загружены", Snackbar.LENGTH_LONG).show()
            }
            UpdateState.Loading -> {
                bindingReal?.resultCurrentConstraintLayoutLoadingLayout?.visibility = View.VISIBLE
            }
            is UpdateState.Error -> {
                bindingReal?.resultCurrentConstraintLayoutLoadingLayout?.visibility = View.GONE
                val throwable = updateState.error
                Snackbar.make(bindingReal?.root!!, "ОШИБКА в загрузке данных: $throwable", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    // Удаление binding при закрытии фрагмента
    override fun onDestroy() {
        super.onDestroy()
        bindingReal = null
    }
}