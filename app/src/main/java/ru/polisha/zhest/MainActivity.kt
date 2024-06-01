package ru.polisha.zhest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.get
import androidx.lifecycle.lifecycleScope

import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch


import kotlinx.serialization.json.Json


class MainActivity : AppCompatActivity() {
    private lateinit var cityEditText: EditText
    private lateinit var fetchWeatherButton: Button
    private lateinit var textViewName: TextView
    private lateinit var temperatureTextView: TextView
    private lateinit var windSpeedTextView: TextView
    private lateinit var weatherDescriptionTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var feelsLikeTextView: TextView

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         cityEditText = findViewById<EditText>(R.id.cityEditText)
         fetchWeatherButton = findViewById<Button>(R.id.fetchWeatherButton)
         textViewName = findViewById<TextView>(R.id.textViewName)
         temperatureTextView = findViewById<TextView>(R.id.temperatureTextView)
         windSpeedTextView = findViewById<TextView>(R.id.windSpeedTextView)
         weatherDescriptionTextView = findViewById<TextView>(R.id.weatherTextView)
         humidityTextView = findViewById<TextView>(R.id.humidityTextView)
         feelsLikeTextView = findViewById<TextView>(R.id.feelsLikeTextView)


        fetchWeatherButton.setOnClickListener {
            val cityName = cityEditText.text.toString()
            if (cityName.isNotEmpty()) {
                fetchWeather(cityName)
            } else {
                Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchWeather(city: String) {
        lifecycleScope.launch {
            try {
                val weather = getWeatherData(city)
                updateUI(weather)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                Log.e("MainActivity", "Error", e)

            }
        }
    }

    private suspend fun getWeatherData(city: String): Weather {
        val response: String = client.get("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&lang=en&appid=6fedf55af13e9e1383c52b10a799ac46").bodyAsText()
        return Json.decodeFromString(response)
    }

    private fun updateUI(weather: Weather) {
        textViewName.text = "City: ${weather.name}"
        temperatureTextView.text = "Temperature: ${weather.main.temp}°C"
        feelsLikeTextView.text = "Feels like: ${weather.main.feels_like}°C"
        windSpeedTextView.text = "Wind Speed: ${weather.wind.speed} m/s"
        weatherDescriptionTextView.text = "Weather: ${weather.weather[0].description}"
        humidityTextView.text = "Humidity: ${weather.main.humidity}%"
    }

    override fun onDestroy() {
        super.onDestroy()
        client.close()
    }
}




