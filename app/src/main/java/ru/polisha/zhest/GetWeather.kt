package ru.polisha.zhest

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch


import kotlinx.serialization.json.Json

private const val SF_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=London&units=metric&lang=en&appid=6fedf55af13e9e1383c52b10a799ac46"



class WeatherViewModel : ViewModel() {
    val weatherData = MutableLiveData<Weather>()

    fun fetchWeather() {
        viewModelScope.launch {
            val client = HttpClient(Android) {
                install(ContentNegotiation) {
                    json()
                }
            }
            val responseBody: String = client.get(SF_WEATHER_URL).bodyAsText()
            val weather: Weather = Json.decodeFromString(responseBody)
            weatherData.postValue(weather)
        }
    }
}




//suspend fun getAndPrintWeather() {
//    val weatherData = MutableLiveData<Weather>()
//    val client = HttpClient(Android) {
//        install(ContentNegotiation) {
//           json()
//        }
//    }
//    val response: HttpResponse = client.get("https://api.openweathermap.org/data/2.5/weather?q=London&units=metric&lang=en&appid=6fedf55af13e9e1383c52b10a799ac46")
//
//    val responseBody: String = response.bodyAsText()
//    val weather: Weather = Json.decodeFromString(responseBody)
//    weatherData.postValue(weather)
////    Log.i("$BASE_TAG Serialization", weather.toString())
//    print(weather)
//
//}







