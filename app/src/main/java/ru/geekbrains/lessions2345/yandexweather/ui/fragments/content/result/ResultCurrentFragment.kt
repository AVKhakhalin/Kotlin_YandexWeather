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
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherDomain
import ru.geekbrains.lessions2345.yandexweather.controller.observers.domain.PublisherGetterDomain
import ru.geekbrains.lessions2345.yandexweather.databinding.FragmentResultCurrentBinding
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModel
import ru.geekbrains.lessions2345.yandexweather.controller.observers.viewmodels.ResultCurrentViewModelSetter
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity

class ResultCurrentFragment : Fragment() {
    // Фабричный метод создания фрагмента
    companion object {
        fun newInstance() = ResultCurrentFragment()
    }

    //region ЗАДАНИЕ ПЕРЕМЕННЫХ
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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_result_current, container, false)
        bindingReal = FragmentResultCurrentBinding.inflate(inflater, container, false)
        return bindingNotReal.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Создание viewModel
//        viewModel = ViewModelProvider(this).get(ResultCurrentViewModel::class.java)
        // Создание observer
//        resultCurrentViewModel.getLiveData().observe(viewLifecycleOwner, Observer<Fact> {fact: Fact ->
        resultCurrentViewModel.getLiveData().observe(viewLifecycleOwner, Observer<DataWeather> {dataWeather: DataWeather ->
           if (dataWeather != null) {
               Toast.makeText(context, "Температура в Москве ${dataWeather.temperature}", Toast.LENGTH_LONG).show()
           } else {
               Toast.makeText(context, "К серверу обратиться не получилось", Toast.LENGTH_LONG).show()
           }
        })
        // Получение данных
//        viewModel.getDataFromRemoteSource()
    }

    // Удаление binding при закрытии фрагмента
    override fun onDestroy() {
        super.onDestroy()
        bindingReal = null
    }
}