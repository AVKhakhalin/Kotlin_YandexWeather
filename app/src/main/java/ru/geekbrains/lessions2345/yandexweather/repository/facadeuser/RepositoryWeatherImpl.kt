package ru.geekbrains.lessions2345.yandexweather.repository.facadeuser

import android.content.Context
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataModel
import ru.geekbrains.lessions2345.yandexweather.domain.data.DataWeather
import ru.geekbrains.lessions2345.yandexweather.domain.data.Fact
import ru.geekbrains.lessions2345.yandexweather.domain.facade.MainChooserSetter
import ru.geekbrains.lessions2345.yandexweather.repository.ConstantsRepository
import ru.geekbrains.lessions2345.yandexweather.ui.activities.MainActivity

class RepositoryWeatherImpl(mainChooserSetter: MainChooserSetter) : RepositoryWeather {
    private val retrofitImpl: RetrofitImpl = RetrofitImpl()
    private val mainChooserSetter: MainChooserSetter = mainChooserSetter

    // Получение данных с сервера Yandex
    override fun getWeatherFromRemoteSource(lat: Double, lon: Double, lang: String) {
        sendServerRequest(lat, lon, lang)
    }

    // Получение данных из локального источника
    override fun getWeatherFromLocalSource(): DataWeather {
        return DataWeather()
    }

    //region МЕТОДЫ ПОЛУЧЕНИЯ ДАННЫХ С СЕРВЕРА YANDEX
    private fun sendServerRequest(lat: Double, lon: Double, lang: String) {
        retrofitImpl.getWeatherApi()
            .getWeather(ConstantsRepository.yandexKeyValue, lat, lon, lang)
            .enqueue(object : Callback<DataModel> {
                override fun onResponse(
                    call: Call<DataModel>,
                    response: Response<DataModel>
                ) {
                    if ((response.isSuccessful) && (response.body() != null)) {
                        saveData(response.body(), lat, lon, null)
                    } else {
                        saveData(null, lat, lon, Throwable("Ответ от сервера пустой"))
                    }
                }

                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    saveData(null, lat, lon, t)
                }
            })
    }

    // Сохранение данных из dataModel в MainChooser (core)
    private fun saveData(dataModel: DataModel?, lat: Double, lon: Double, error: Throwable?) {
        if (mainChooserSetter != null) {
            mainChooserSetter.setDataModel(dataModel, lat, lon)
        }
    }
    //endregion
}

interface WeatherAPI {
    @GET("v2/informers")
    fun getWeather(
        @Header("X-Yandex-API-Key") token: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String
    ): Call<DataModel>
}

class RetrofitImpl {
    fun getWeatherApi(): WeatherAPI {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ConstantsRepository.baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
        return retrofit.create(WeatherAPI::class.java)
    }
}