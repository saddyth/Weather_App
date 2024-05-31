package ru.polisha.zhest

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch
import androidx.activity.viewModels
import ru.polisha.zhest.WeatherViewModel
import androidx.lifecycle.Observer
class MainActivity : AppCompatActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        val textViewCoord = findViewById<TextView>(R.id.textViewCoord)
        weatherViewModel.weatherData.observe(this, Observer { weather ->
            weather?.let {
                // Обновляем UI при получении данных
                textViewCoord.text = "Coordinates: ${it.coord.lon}, ${it.coord.lat}"
                // Обновите другие элементы интерфейса
            }
        })

        weatherViewModel.fetchWeather()


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


    companion object {
        const val BASE_TAG = "### "
    }
}