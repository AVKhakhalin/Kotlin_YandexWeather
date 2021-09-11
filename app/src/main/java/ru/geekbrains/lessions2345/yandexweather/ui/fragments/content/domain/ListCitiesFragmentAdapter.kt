package ru.geekbrains.lesson_1423_2_2_main.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.lessions2345.yandexweather.R
import ru.geekbrains.lessions2345.yandexweather.domain.data.City
import ru.geekbrains.lesson_1423_2_2_main.view.OnItemViewClickListener

class ListCitiesFragmentAdapter:RecyclerView.Adapter<ListCitiesFragmentAdapter.MainFragmentViewHolder>() {

    private var weatherData: List<City> = listOf()
    private var isDataSetRus: Boolean = true
    private lateinit var  listener: OnItemViewClickListener
    fun setWeather(data:List<City>){
        weatherData = data
        notifyDataSetChanged()
    }

    fun setOnItemViewClickListener(onItemViewClickListener:OnItemViewClickListener){
        listener = onItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val holder = MainFragmentViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_list_cities_recycler_item,parent,false))
        return holder;
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    override fun getItemCount() = weatherData.size

     inner class MainFragmentViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun render(city: City){
            itemView.findViewById<TextView>(R.id.recycler_item_text_view).text = city.name
            itemView.setOnClickListener( object : View.OnClickListener{
                override fun onClick(p0: View?) {
                    Toast.makeText(itemView.context,"РАБОТАЕТ ${city.name}", Toast.LENGTH_SHORT).show()
                    listener.onItemClick(city)
                }
            })
        }
    }
}