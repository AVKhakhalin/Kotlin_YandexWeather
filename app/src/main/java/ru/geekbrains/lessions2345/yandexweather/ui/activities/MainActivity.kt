package ru.geekbrains.lessions2345.yandexweather.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.ui.fragments.content.result.ResultCurrentFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Отображение фрагмента
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_result_weather_container, ResultCurrentFragment.newInstance()).commit()
        }
    }
}