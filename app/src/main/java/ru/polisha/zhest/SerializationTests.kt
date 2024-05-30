package ru.polisha.zhest

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import ru.polisha.zhest.MainActivity.Companion.BASE_TAG

private const val SF_WEATHER_URL = "https://www.gismeteo.ru/api/"

suspend fun getAndPrintWeather() {

    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
//            serializer = GsonSerializer()
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        expectSuccess = true
    }
    val weather: HttpResponse = client.get(SF_WEATHER_URL)
    Log.i("$BASE_TAG Serialization", weather.toString())
    client.close()
    println(weather)
}
