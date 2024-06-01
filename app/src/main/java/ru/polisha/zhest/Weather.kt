package ru.polisha.zhest
import kotlinx.serialization.Serializable
@Serializable
data class Weather(
    val coord: Coord,
    val weather: List<WeatherData>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val rain: Rain? = null,
    val snow: Snow? = null,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

@Serializable
data class Coord(
    val lon: Double,
    val lat: Double
)

@Serializable
data class WeatherData(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int? = null,
    val grnd_level: Int? = null
)

@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double? = null
)
@Serializable
data class Rain(
    val `1h`: Double? = null,
    val `3h`: Double? = null
)
@Serializable
data class Snow(
    val `1h`: Double? = null,
    val `3h`: Double? = null
)
@Serializable
data class Clouds(
    val all: Int
)

@Serializable
data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)