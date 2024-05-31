package ru.polisha.zhest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import android.util.Log
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch


import kotlinx.serialization.json.Json
class MainActivity : AppCompatActivity() {
    private lateinit var temperatureTextView: TextView
    private lateinit var windSpeedTextView: TextView
    private lateinit var weatherDescriptionTextView: TextView
    private lateinit var textViewName: TextView

    val SF_WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=London&units=metric&lang=en&appid=6fedf55af13e9e1383c52b10a799ac46"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        temperatureTextView = findViewById(R.id.temperatureTextView)
        windSpeedTextView = findViewById(R.id.windSpeedTextView)
        weatherDescriptionTextView = findViewById(R.id.weatherDescriptionTextView)
        textViewName = findViewById(R.id.textViewName)

        // Инициируем загрузку данных погоды
        fetchWeather()
    }

    private fun fetchWeather() {
        print("ok")
        lifecycleScope.launch {
            val client = HttpClient(Android) {
                install(ContentNegotiation) {
                    json()
                }
            }
            try {
                Log.d("MainActivity", "Sending HTTP request")
                val response: String = client.get(SF_WEATHER_URL).bodyAsText()
                Log.d("MainActivity", "Response received: $response")
                val weather: Weather = Json.decodeFromString(response)
                updateUI(weather)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error fetching weather data", e)
            }
        }
    }

    private fun updateUI(weather: Weather) {
        Log.d("MainActivity", "Weather data updated: $weather")
        temperatureTextView.text = "Temperature: ${weather.main.temp}°C"
        windSpeedTextView.text = "Wind Speed: ${weather.wind.speed} m/s"
        weatherDescriptionTextView.text = "Weather: ${weather.weather[0].description}"
        textViewName.text = weather.name

    }




//
//    private val weatherViewModel: WeatherViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//            super.onCreate(savedInstanceState)
//            setContentView(R.layout.activity_main)
//        val textViewCoord = findViewById<TextView>(R.id.textViewCoord)
//        weatherViewModel.weatherData.observe(this, Observer { weather ->
//            weather?.let {
//                // Обновляем UI при получении данных
//                textViewCoord.text = "Coordinates: ${it.coord.lon}, ${it.coord.lat}"
//                // Обновите другие элементы интерфейса
//            }
//        })
//
//        weatherViewModel.fetchWeather()


//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContentView(R.layout.activity_main)
//        performAllCases()
//
//
//        val view = findViewById<View>(R.id.main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//
//        }


//        val startButton = view.findViewById<Button>(R.id.startButton)
//        val endButton = view.findViewById<Button>(R.id.endButton)
//        val checkBox1 = view.findViewById<CheckBox>(R.id.checkBox)
//
//        startButton.isEnabled = false
//        endButton.isEnabled = false
//
//        val icon = view.findViewById<ImageView>(R.id.icon)
//        icon.setOnClickListener{
//            endButton.isVisible = !endButton.isVisible
//        }
//
//        val buttonClickToast = Toast.makeText(this, "You are ready", Toast.LENGTH_SHORT)
//
//        endButton.setOnClickListener{
//            startButton.isEnabled = !startButton.isEnabled
//        }
//
//        startButton.setOnClickListener{
//            buttonClickToast.show()
//        }
//
//        checkBox1.setOnCheckedChangeListener { _, isChecked ->
//            if (!isChecked){
//                endButton.isEnabled = false
//            } else {
//                endButton.isEnabled = true
//            }
//        }
    }



