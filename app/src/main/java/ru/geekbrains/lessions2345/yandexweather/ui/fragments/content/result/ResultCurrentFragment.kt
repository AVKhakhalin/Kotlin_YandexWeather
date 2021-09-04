package ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.databinding.FragmentResultCurrentBinding
import ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result.viewmodel.ResultCurrentViewModel

class ResultCurrentFragment: Fragment() {
    // Фабричный метод создания фрагмента
    companion object {
        fun newInstance() = ResultCurrentFragment()
    }

    // Ссылка на ResultCurrentViewModel
    private lateinit var viewModel: ResultCurrentViewModel

    // Создание binding с возможностью удаления (имя класса FragmentResultCurrentBinding формируется из класса ResultCurrentFragment)
    // Класс FragmentResultCurrentBinding - представление макета fragment_result_current.xml в виде кода
    private var bindingForDelete: FragmentResultCurrentBinding? = null
    private val binding: FragmentResultCurrentBinding
    get() {
        return bindingForDelete!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_result_current, container, false)
        bindingForDelete = FragmentResultCurrentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Создание viewModel
        viewModel = ViewModelProvider(this).get(ResultCurrentViewModel::class.java)
        // Создание observer
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<Any>{
            Toast.makeText(context, "its work", Toast.LENGTH_LONG).show()
        })
        // Получение данных
        viewModel.getDataFromRemoteSource()
    }

    // Удаление binding при закрытии фрагмента
    override fun onDestroy() {
        super.onDestroy()
        bindingForDelete = null
    }
}